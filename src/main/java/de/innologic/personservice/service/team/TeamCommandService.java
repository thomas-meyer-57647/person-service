package de.innologic.personservice.service.team;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.domain.Team;
import de.innologic.personservice.domain.TeamMember;
import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import de.innologic.personservice.mapper.TeamMapper;
import de.innologic.personservice.mapper.TeamMemberMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.repository.TeamMemberRepository;
import de.innologic.personservice.repository.TeamRepository;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TeamCommandService {

    private final TeamRepository teamRepository;
    private final PersonRepository personRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;

    public TeamCommandService(
            TeamRepository teamRepository,
            PersonRepository personRepository,
            TeamMemberRepository teamMemberRepository,
            TeamMapper teamMapper,
            TeamMemberMapper teamMemberMapper
    ) {
        this.teamRepository = teamRepository;
        this.personRepository = personRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.teamMapper = teamMapper;
        this.teamMemberMapper = teamMemberMapper;
    }

    public TeamResponse createTeam(Long companyId, TeamCreateRequest request, String actorId) {
        validateCompany(request.getCompanyId(), companyId);
        teamRepository.findByCompanyIdAndNameAndAudit_TrashedAtIsNull(companyId, request.getName())
                .ifPresent(team -> {
                    throw new ConflictException("Team name already exists in this company.");
                });
        Team team = teamMapper.toEntity(request);
        team.setCompanyId(companyId);
        team.getAudit().setCreatedBy(actor(actorId));
        team.getAudit().setModifiedBy(actor(actorId));
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse updateTeam(Long companyId, Long teamId, TeamUpdateRequest request, String actorId) {
        Team team = teamRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        if (request.getName() != null && !request.getName().equals(team.getName())) {
            teamRepository.findByCompanyIdAndNameAndAudit_TrashedAtIsNull(companyId, request.getName())
                    .ifPresent(existing -> {
                        throw new ConflictException("Team name already exists in this company.");
                    });
        }
        teamMapper.updateEntity(request, team);
        team.getAudit().setModifiedBy(actor(actorId));
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse trashTeam(Long companyId, Long teamId, String actorId) {
        Team team = teamRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        team.getAudit().setTrashedAt(LocalDateTime.now());
        team.getAudit().setTrashedBy(actor(actorId));
        team.getAudit().setModifiedBy(actor(actorId));
        teamMemberRepository.findAllByCompanyIdAndTeam_IdAndAudit_TrashedAtIsNull(companyId, teamId)
                .forEach(member -> {
                    member.getAudit().setTrashedAt(LocalDateTime.now());
                    member.getAudit().setTrashedBy(actor(actorId));
                    member.getAudit().setModifiedBy(actor(actorId));
                    if (member.getLeftAt() == null) {
                        member.setLeftAt(LocalDateTime.now());
                    }
                });
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse restoreTeam(Long companyId, Long teamId, String actorId) {
        Team team = teamRepository.findByIdAndCompanyId(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        team.getAudit().setTrashedAt(null);
        team.getAudit().setTrashedBy(null);
        team.getAudit().setModifiedBy(actor(actorId));
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamMemberResponse addTeamMember(Long companyId, Long teamId, TeamMemberAddRequest request, String actorId) {
        Team team = teamRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        Person person = personRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(request.getPersonId(), companyId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(companyId, teamId, request.getPersonId())
                .ifPresent(existing -> {
                    throw new ConflictException("Person is already an active member of this team.");
                });
        TeamMember teamMember = teamMemberMapper.toEntity(request);
        teamMember.setCompanyId(companyId);
        teamMember.setTeam(team);
        teamMember.setPerson(person);
        teamMember.getAudit().setCreatedBy(actor(actorId));
        teamMember.getAudit().setModifiedBy(actor(actorId));
        return teamMemberMapper.toResponse(teamMemberRepository.save(teamMember));
    }

    public void removeTeamMember(Long companyId, Long teamId, Long personId, String actorId) {
        TeamMember teamMember = teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(companyId, teamId, personId)
                .orElseThrow(() -> new NotFoundException("Active team member relation not found."));
        teamMember.getAudit().setTrashedAt(LocalDateTime.now());
        teamMember.getAudit().setTrashedBy(actor(actorId));
        teamMember.getAudit().setModifiedBy(actor(actorId));
        if (teamMember.getLeftAt() == null) {
            teamMember.setLeftAt(LocalDateTime.now());
        }
        teamMemberRepository.save(teamMember);
    }

    private void validateCompany(Long bodyCompanyId, Long pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }

    private String actor(String actorId) {
        if (actorId == null || actorId.isBlank()) {
            return "system";
        }
        return actorId.trim();
    }
}
