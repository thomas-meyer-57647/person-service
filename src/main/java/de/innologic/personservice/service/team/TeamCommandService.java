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
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class TeamCommandService {

    private final TeamRepository teamRepository;
    private final PersonRepository personRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;
    private final CurrentActor currentActor;

    public TeamCommandService(
            TeamRepository teamRepository,
            PersonRepository personRepository,
            TeamMemberRepository teamMemberRepository,
            TeamMapper teamMapper,
            TeamMemberMapper teamMemberMapper,
            CurrentActor currentActor
    ) {
        this.teamRepository = teamRepository;
        this.personRepository = personRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.teamMapper = teamMapper;
        this.teamMemberMapper = teamMemberMapper;
        this.currentActor = currentActor;
    }

    public TeamResponse createTeam(String companyId, TeamCreateRequest request, String actorId) {
        String actor = currentActor.subjectOrSystem();
        validateCompany(request.getCompanyId(), companyId);
        teamRepository.findByCompanyIdAndNameAndAudit_TrashedAtIsNull(companyId, request.getName())
                .ifPresent(team -> {
                    throw new ConflictException("Team name already exists in this company.");
        });
        Team team = teamMapper.toEntity(request);
        team.setTeamId(UUID.randomUUID().toString());
        team.setCompanyId(companyId);
        team.getAudit().setCreatedBy(actor);
        team.getAudit().setModifiedBy(actor);
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse updateTeam(String companyId, String teamId, TeamUpdateRequest request, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Team team = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        if (request.getName() != null && !request.getName().equals(team.getName())) {
            teamRepository.findByCompanyIdAndNameAndAudit_TrashedAtIsNull(companyId, request.getName())
                    .ifPresent(existing -> {
                        throw new ConflictException("Team name already exists in this company.");
                    });
        }
        teamMapper.updateEntity(request, team);
        team.getAudit().setModifiedBy(actor);
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse trashTeam(String companyId, String teamId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Team team = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        team.getAudit().setTrashedAt(LocalDateTime.now());
        team.getAudit().setTrashedBy(actor);
        team.getAudit().setModifiedBy(actor);
        teamMemberRepository.findAllByCompanyIdAndTeam_IdAndAudit_TrashedAtIsNull(companyId, team.getId())
                .forEach(member -> {
                    member.getAudit().setTrashedAt(LocalDateTime.now());
                    member.getAudit().setTrashedBy(actor);
                    member.getAudit().setModifiedBy(actor);
                    if (member.getLeftAt() == null) {
                        member.setLeftAt(LocalDateTime.now());
                    }
                });
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamResponse restoreTeam(String companyId, String teamId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Team team = teamRepository.findByTeamIdAndCompanyId(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        team.getAudit().setTrashedAt(null);
        team.getAudit().setTrashedBy(null);
        team.getAudit().setModifiedBy(actor);
        return teamMapper.toResponse(teamRepository.save(team));
    }

    public TeamMemberResponse addTeamMember(String companyId, String teamId, TeamMemberAddRequest request, String actorId) {
        String actor = currentActor.subjectOrSystem();
        LocalDateTime now = LocalDateTime.now();
        Team team = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        Person person = personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, request.getPersonId())
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        if (Boolean.TRUE.equals(request.getIsPrimary())) {
            teamMemberRepository.findByCompanyIdAndPerson_IdAndIsPrimaryTrueAndLeftAtIsNullAndAudit_TrashedAtIsNull(companyId, person.getId())
                    .ifPresent(existing -> {
                        throw new ConflictException("Person already has an active primary membership.");
                    });
        }
        teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(companyId, team.getId(), person.getId())
                .ifPresent(existing -> {
                    throw new ConflictException("Person is already an active member of this team.");
                });
        TeamMember teamMember = teamMemberMapper.toEntity(request);
        teamMember.setCompanyId(companyId);
        teamMember.setTeam(team);
        teamMember.setPerson(person);
        teamMember.setMembershipId(UUID.randomUUID().toString());
        teamMember.setJoinedAt(resolveJoinedAt(request.getJoinedAt(), now));
        teamMember.setIsPrimary(Boolean.TRUE.equals(request.getIsPrimary()));
        teamMember.getAudit().setCreatedBy(actor);
        teamMember.getAudit().setModifiedBy(actor);
        return teamMemberMapper.toResponse(teamMemberRepository.save(teamMember));
    }

    public void removeTeamMember(String companyId, String teamId, String personId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Team team = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        Person person = personRepository.findByCompanyIdAndPublicId(companyId, personId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        TeamMember teamMember = teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(companyId, team.getId(), person.getId())
                .orElseThrow(() -> new NotFoundException("Active team member relation not found."));
        teamMember.getAudit().setTrashedAt(LocalDateTime.now());
        teamMember.getAudit().setTrashedBy(actor);
        teamMember.getAudit().setModifiedBy(actor);
        if (teamMember.getLeftAt() == null) {
            teamMember.setLeftAt(LocalDateTime.now());
        }
        teamMemberRepository.save(teamMember);
    }

    private void validateCompany(String bodyCompanyId, String pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }

    private LocalDateTime resolveJoinedAt(LocalDateTime requested, LocalDateTime current) {
        if (requested == null) {
            return current;
        }
        if (requested.isAfter(current)) {
            throw new BadRequestException("joinedAt must be in the past or present.");
        }
        return requested;
    }

}

