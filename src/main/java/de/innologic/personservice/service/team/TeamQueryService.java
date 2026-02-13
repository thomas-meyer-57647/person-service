package de.innologic.personservice.service.team;

import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.mapper.TeamMapper;
import de.innologic.personservice.mapper.TeamMemberMapper;
import de.innologic.personservice.repository.TeamMemberRepository;
import de.innologic.personservice.repository.TeamRepository;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TeamQueryService {

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

    public Page<TeamResponse> listTeams(Long companyId, String q, boolean includeTrashed, Pageable pageable) {
        return teamRepository.search(companyId, normalize(q), includeTrashed, pageable).map(teamMapper::toResponse);
    }

    public TeamResponse getTeam(Long companyId, Long teamId) {
        return teamRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .map(teamMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
    }

    public List<TeamMemberResponse> getTeamMembers(Long companyId, Long teamId) {
        teamRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(teamId, companyId)
                .orElseThrow(() -> new NotFoundException("Team not found for company and id."));
        return teamMemberRepository.findAllByCompanyIdAndTeam_IdAndAudit_TrashedAtIsNull(companyId, teamId).stream()
                .map(teamMemberMapper::toResponse)
                .toList();
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
