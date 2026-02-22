package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import de.innologic.personservice.service.team.TeamCommandService;
import io.swagger.v3.oas.annotations.Operation;
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
public class TeamCommandController {

    private final TeamCommandService teamCommandService;

    public TeamCommandController(TeamCommandService teamCommandService) {
        this.teamCommandService = teamCommandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse createTeam(
            @PathVariable String companyId,
            @Valid @RequestBody TeamCreateRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.createTeam(companyId, request, actorId);
    }

    @PatchMapping("/{teamId}")
    @Operation(summary = "Update team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse updateTeam(
            @PathVariable String companyId,
            @PathVariable Long teamId,
            @Valid @RequestBody TeamUpdateRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.updateTeam(companyId, teamId, request, actorId);
    }

    @PostMapping("/{teamId}/trash")
    @Operation(summary = "Trash team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse trashTeam(
            @PathVariable String companyId,
            @PathVariable Long teamId,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.trashTeam(companyId, teamId, actorId);
    }

    @PostMapping("/{teamId}/restore")
    @Operation(summary = "Restore team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamResponse restoreTeam(
            @PathVariable String companyId,
            @PathVariable Long teamId,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.restoreTeam(companyId, teamId, actorId);
    }

    @PostMapping("/{teamId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add member to team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public TeamMemberResponse addMember(
            @PathVariable String companyId,
            @PathVariable Long teamId,
            @Valid @RequestBody TeamMemberAddRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return teamCommandService.addTeamMember(companyId, teamId, request, actorId);
    }

    @DeleteMapping("/{teamId}/members/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove member from team")
    @PreAuthorize("hasAuthority('SCOPE_team:write')")
    public void removeMember(
            @PathVariable String companyId,
            @PathVariable Long teamId,
            @PathVariable String personId,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        teamCommandService.removeTeamMember(companyId, teamId, personId, actorId);
    }
}

