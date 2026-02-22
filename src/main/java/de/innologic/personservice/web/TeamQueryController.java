package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.service.team.TeamQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/teams")
@Tag(name = "Teams Query")
public class TeamQueryController {

    private final TeamQueryService teamQueryService;

    public TeamQueryController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    @GetMapping
    @Operation(summary = "List teams by company")
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public Page<TeamResponse> listTeams(
            @PathVariable String companyId,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "false") boolean includeTrashed,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return teamQueryService.listTeams(companyId, q, includeTrashed, pageable);
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Get team by id")
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public TeamResponse getTeam(@PathVariable String companyId, @PathVariable Long teamId) {
        return teamQueryService.getTeam(companyId, teamId);
    }

    @GetMapping("/{teamId}/members")
    @Operation(summary = "List members of a team")
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public List<TeamMemberResponse> getTeamMembers(@PathVariable String companyId, @PathVariable Long teamId) {
        return teamQueryService.getTeamMembers(companyId, teamId);
    }
}

