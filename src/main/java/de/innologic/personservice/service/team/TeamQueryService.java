package de.innologic.personservice.service.team;

import de.innologic.personservice.domain.Team;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.mapper.TeamMapper;
import de.innologic.personservice.mapper.TeamMemberMapper;
import de.innologic.personservice.repository.TeamMemberRepository;
import de.innologic.personservice.repository.TeamRepository;
import de.innologic.personservice.web.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(TeamQueryService.class);


    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamMapper teamMapper;
    private final TeamMemberMapper teamMemberMapper;

    public TeamQueryService(
            TeamRepository teamRepository,
            TeamMemberRepository teamMemberRepository,
            TeamMapper teamMapper,
            TeamMemberMapper teamMemberMapper
    ) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.teamMapper = teamMapper;
        this.teamMemberMapper = teamMemberMapper;
    }

    public Page<TeamResponse> listTeams(String companyId, String q, boolean includeTrashed, Pageable pageable) {
        LOG.info("Listing teams company={} includeTrashed={} query={}", companyId, includeTrashed, q);
        Page<TeamResponse> teams = teamRepository.search(companyId, normalize(q), includeTrashed, pageable)
                .map(teamMapper::toResponse);
        LOG.info("Found {} teams for company={} includeTrashed={}", teams.getNumberOfElements(), companyId, includeTrashed);
        return teams;
    }

    public TeamResponse getTeam(String companyId, String teamId) {
        LOG.info("Fetching team company={} team={}", companyId, teamId);
        TeamResponse response = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .map(teamMapper::toResponse)
                .orElseThrow(() -> {
                    LOG.warn("Team not found for company={} team={}", companyId, teamId);
                    return new NotFoundException("Team not found for company and id.");
                });
        LOG.info("Found teamId={} for company={}", response.getTeamId(), companyId);
        return response;
    }

    public List<TeamMemberResponse> getTeamMembers(String companyId, String teamId) {
        LOG.info("Get team members request company={} team={}", companyId, teamId);
        Team team = teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> {
                    LOG.warn("Team not found when listing members company={} team={}", companyId, teamId);
                    return new NotFoundException("Team not found for company and id.");
                });
        List<TeamMemberResponse> members = teamMemberRepository.findAllByCompanyIdAndTeam_IdAndAudit_TrashedAtIsNull(companyId, team.getId()).stream()
                .map(teamMemberMapper::toResponse)
                .toList();
        LOG.info("Returning {} members for team={} company={}", members.size(), teamId, companyId);
        return members;
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}

