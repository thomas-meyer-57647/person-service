package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import de.innologic.personservice.service.team.TeamCommandService;
import de.innologic.personservice.web.error.ProblemDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/teams")
@Tag(name = "Teams Command")
@SecurityRequirement(name = "bearerAuth")
public class TeamCommandController {

    private final TeamCommandService teamCommandService;

    public TeamCommandController(TeamCommandService teamCommandService) {
        this.teamCommandService = teamCommandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create team",
            description = "Register a new team inside the company scope and return its stable public teamId.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Team created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed or companyId mismatch.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "409", description = "Team name already exists in the tenant.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse createTeam(
            @Parameter(description = "Tenant (company) ID that also scopes the JWT.", required = true, example = "company-123")
            @PathVariable String companyId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Team payload with name and optional description.", required = true,
                    content = @Content(schema = @Schema(implementation = TeamCreateRequest.class)))
            @Valid @RequestBody TeamCreateRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.createTeam(companyId, request, actorId);
    }

    @PatchMapping("/{teamId}")
    @Operation(summary = "Update team",
            description = "Update the team's name or description while keeping the public teamId stable.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team updated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "409", description = "Team name already exists in the tenant.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse updateTeam(
            @Parameter(description = "Tenant (company) ID that scopes the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId of the team to update.", required = true, example = "team-123")
            @PathVariable String teamId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partial payload for the team.",
                    required = true, content = @Content(schema = @Schema(implementation = TeamUpdateRequest.class)))
            @Valid @RequestBody TeamUpdateRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.updateTeam(companyId, teamId, request, actorId);
    }

    @PostMapping("/{teamId}/trash")
    @Operation(summary = "Trash team",
            description = "Move a team into the trashed state while keeping the public IDs and audit data accessible.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team trashed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse trashTeam(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId of the team to trash.", required = true, example = "team-123")
            @PathVariable String teamId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.trashTeam(companyId, teamId, actorId);
    }

    @PostMapping("/{teamId}/restore")
    @Operation(summary = "Restore team",
            description = "Re-activate a previously trashed team while keeping its public teamId stable.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team restored.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse restoreTeam(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId of the team to restore.", required = true, example = "team-123")
            @PathVariable String teamId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.restoreTeam(companyId, teamId, actorId);
    }

    @PostMapping("/{teamId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add member to team",
            description = "Add a person to a team via their public personId and optionally mark the membership as primary.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Membership created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamMemberResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team or person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "409", description = "Person already an active member of this team.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamMemberResponse addMember(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId to add the member to.", required = true, example = "team-123")
            @PathVariable String teamId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Membership payload referencing a public personId and optional joinedAt/isPrimary.",
                    required = true, content = @Content(schema = @Schema(implementation = TeamMemberAddRequest.class)))
            @Valid @RequestBody TeamMemberAddRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.addTeamMember(companyId, teamId, request, actorId);
    }

    @DeleteMapping("/{teamId}/members/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove member from team",
            description = "Soft-delete the membership and record the actor. The returned response is empty when successful.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Membership removed."),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Team, person, or active membership not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public void removeMember(
            @Parameter(description = "Tenant (company) ID owning the team.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public teamId from which to remove the member.", required = true, example = "team-123")
            @PathVariable String teamId,
            @Parameter(description = "Public personId identifying the member to remove.", required = true, example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123")
            @PathVariable String personId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        teamCommandService.removeTeamMember(companyId, teamId, personId, actorId);
    }
}

