package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.service.team.TeamQueryService;
import de.innologic.personservice.web.error.ProblemDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SecurityRequirement(name = "bearerAuth")
public class TeamQueryController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamQueryController.class);

    private final TeamQueryService teamQueryService;

    public TeamQueryController(TeamQueryService teamQueryService) {
        this.teamQueryService = teamQueryService;
    }

    @GetMapping
    @Operation(summary = "List teams by company",
            description = "Page through teams for the tenant, optionally including trashed records and filtering by free text.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of teams."),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public Page<TeamResponse> listTeams(
            @Parameter(description = "Tenant (company) ID scoping the teams.", required = true, example = "company-123")
            @PathVariable String companyId,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "false") boolean includeTrashed,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        LOG.info("List teams request company={} includeTrashed={} query={}", companyId, includeTrashed, q);
        Page<TeamResponse> teams = teamQueryService.listTeams(companyId, q, includeTrashed, pageable);
        LOG.info("Returning {} teams for company={} includeTrashed={} query={}", teams.getNumberOfElements(), companyId, includeTrashed, q);
        return teams;
    }

    @GetMapping("/{teamId}")
    @Operation(summary = "Get team by id",
            description = "Fetch the requested team if it exists inside the current tenant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team details.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public TeamResponse getTeam(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId for the team.", required = true, example = "team-123")
            @PathVariable String teamId) {
        LOG.info("Get team request company={} team={}", companyId, teamId);
        TeamResponse response = teamQueryService.getTeam(companyId, teamId);
        LOG.info("Found teamId={} for company={}", response.getTeamId(), companyId);
        return response;
    }

    @GetMapping("/{teamId}/members")
    @Operation(summary = "List members of a team",
            description = "Return the current active memberships of a team reference.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of memberships."),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:read')")
    public List<TeamMemberResponse> getTeamMembers(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId whose members should be returned.", required = true, example = "team-123")
            @PathVariable String teamId) {
        LOG.info("Get team members request company={} team={}", companyId, teamId);
        List<TeamMemberResponse> members = teamQueryService.getTeamMembers(companyId, teamId);
        LOG.info("Returning {} members for team={} company={}", members.size(), teamId, companyId);
        return members;
    }
}

