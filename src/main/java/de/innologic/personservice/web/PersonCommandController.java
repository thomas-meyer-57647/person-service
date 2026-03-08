package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.service.person.PersonCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/persons")
@Tag(name = "Persons Command")
public class PersonCommandController {

    private final PersonCommandService personCommandService;

    public PersonCommandController(PersonCommandService personCommandService) {
        this.personCommandService = personCommandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create person",
            description = "Register a tenant-specific person identified by firstName/lastName/displayName/notes. The returned PersonResponse always exposes contactOwnerType=PERSON and contactOwnerId equal to the public personId so downstream contact services can dock their contact points.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Person created (contactOwnerType stays PERSON).",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed or companyId mismatch.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:write')")
    public PersonResponse createPerson(
            @Parameter(description = "Tenant (company) ID and JWT tenant claim that scopes the new person.", required = true, example = "company-123")
            @PathVariable String companyId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Person payload containing first/last name, optional displayName and notes. Contact data is kept out of this service and is exposed via contactOwner fields in the response.",
                    required = true, content = @Content(schema = @Schema(implementation = PersonCreateRequest.class)))
            @Valid @RequestBody PersonCreateRequest request,
            @Parameter(description = "Optional actor identifier for auditing; defaults to the authenticated JWT subject or system.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.createPerson(companyId, request, actorId);
    }

    @PatchMapping("/{personId}")
    @Operation(summary = "Update person",
            description = "Partial update of a person record, keeping the contact owner values stable while firstName, lastName, displayName or notes change.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person updated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:write')")
    public PersonResponse updatePerson(
            @PathVariable String companyId,
            @Parameter(description = "Public personId from the company scope.", required = true, example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123")
            @PathVariable String personId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Fields to patch on the person entity (firstName, lastName, displayName, notes).",
                    required = true, content = @Content(schema = @Schema(implementation = PersonUpdateRequest.class)))
            @Valid @RequestBody PersonUpdateRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.updatePerson(companyId, personId, request, actorId);
    }

    @PostMapping("/{personId}/trash")
    @Operation(summary = "Trash person",
            description = "Move the person record into TRASHED state while keeping contact owner metadata available for downstream reconciliation.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person trashed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:write')")
    public PersonResponse trashPerson(
            @PathVariable String companyId,
            @Parameter(description = "Public personId identifying the record to trash.", required = true)
            @PathVariable String personId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.trashPerson(companyId, personId, actorId);
    }

    @PostMapping("/{personId}/restore")
    @Operation(summary = "Restore person",
            description = "Re-activate a trashed person record while retaining the contact owner docking links for downstream consumers.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person restored.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:write')")
    public PersonResponse restorePerson(
            @PathVariable String companyId,
            @Parameter(description = "Public personId identifying the record to restore.", required = true)
            @PathVariable String personId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.restorePerson(companyId, personId, actorId);
    }
}

