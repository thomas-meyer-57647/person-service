package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonClassificationAssignmentRequest;
import de.innologic.personservice.dto.PersonClassificationAssignmentResponse;
import de.innologic.personservice.dto.PersonClassificationCreateRequest;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.dto.PersonClassificationUpdateRequest;
import de.innologic.personservice.service.classification.PersonClassificationAssignmentService;
import de.innologic.personservice.service.classification.PersonClassificationCommandService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies/{companyId}")
@Tag(name = "Person Classifications Command")
@SecurityRequirement(name = "bearerAuth")
public class PersonClassificationCommandController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonClassificationCommandController.class);

    private final PersonClassificationCommandService classificationCommandService;
    private final PersonClassificationAssignmentService assignmentService;

    public PersonClassificationCommandController(
            PersonClassificationCommandService classificationCommandService,
            PersonClassificationAssignmentService assignmentService
    ) {
        this.classificationCommandService = classificationCommandService;
        this.assignmentService = assignmentService;
    }

    @PostMapping("/person-classifications")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create classification", description = "Register a new classification entry for the requested tenant.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Classification created.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonClassificationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation or tenant mismatch error.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "409", description = "Classification key/code already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:classification:write')")
    public PersonClassificationResponse createClassification(
            @Parameter(description = "Tenant (company) ID that scopes the classification.", required = true, example = "company-123")
            @PathVariable String companyId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Classification payload with key, code and label.", required = true,
                    content = @Content(schema = @Schema(implementation = PersonClassificationCreateRequest.class)))
            @Valid @RequestBody PersonClassificationCreateRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("Create classification request company={} key={} code={} actor={}", companyId, request.getKey(), request.getCode(), actorId);
        PersonClassificationResponse response = classificationCommandService.createClassification(companyId, request, actorId);
        LOG.info("Created classificationId={} for company={}", response.getClassificationId(), companyId);
        return response;
    }

    @PatchMapping("/person-classifications/{classificationId}")
    @Operation(summary = "Change classification", description = "Adjust label, key or code of an existing classification.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Classification updated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonClassificationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Classification not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "409", description = "Classification key/code already exists.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:classification:write')")
    public PersonClassificationResponse updateClassification(
            @Parameter(description = "Tenant (company) ID that scopes the classification.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public classification ID to patch.", required = true, example = "classification-123")
            @PathVariable String classificationId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partial classification payload.",
                    required = true, content = @Content(schema = @Schema(implementation = PersonClassificationUpdateRequest.class)))
            @Valid @RequestBody PersonClassificationUpdateRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("Update classification request company={} classification={} actor={}", companyId, classificationId, actorId);
        PersonClassificationResponse response = classificationCommandService.updateClassification(companyId, classificationId, request, actorId);
        LOG.info("Updated classificationId={} for company={}", response.getClassificationId(), companyId);
        return response;
    }

    @PostMapping("/person-classifications/{classificationId}/deactivate")
    @Operation(summary = "Deactivate classification", description = "Mark a classification as inactive so it can no longer be assigned.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Classification deactivated.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonClassificationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Classification not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:classification:write')")
    public PersonClassificationResponse deactivateClassification(
            @Parameter(description = "Tenant (company) ID that scopes the classification.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public classification ID to deactivate.", required = true, example = "classification-123")
            @PathVariable String classificationId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("Deactivate classification request company={} classification={} actor={}", companyId, classificationId, actorId);
        PersonClassificationResponse response = classificationCommandService.deactivateClassification(companyId, classificationId, actorId);
        LOG.info("Deactivated classificationId={} for company={}", response.getClassificationId(), companyId);
        return response;
    }

    @PostMapping("/persons/{personId}/classifications")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Assign classification to person", description = "Attach a classification to a person record.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Classification assigned.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonClassificationAssignmentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation or inactive classification.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Person or classification not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:classification:write')")
    public PersonClassificationAssignmentResponse assignClassification(
            @Parameter(description = "Tenant (company) ID that scopes the assignment.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public person ID that receives the classification.", required = true, example = "person-123")
            @PathVariable String personId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assignment payload referencing a classification.",
                    required = true, content = @Content(schema = @Schema(implementation = PersonClassificationAssignmentRequest.class)))
            @Valid @RequestBody PersonClassificationAssignmentRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("Assign classification request company={} person={} classification={} actor={}", companyId, personId, request.getClassificationId(), actorId);
        PersonClassificationAssignmentResponse response = assignmentService.assignClassification(companyId, personId, request, actorId);
        LOG.info("Created assignmentId={} for person={} classification={}", response.getAssignmentId(), personId, response.getClassificationId());
        return response;
    }

    @DeleteMapping("/persons/{personId}/classifications/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove classification assignment", description = "Soft-remove an assignment from a person.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Assignment removed."),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:classification:write')")
    public void removeAssignment(
            @Parameter(description = "Tenant (company) ID that scopes the assignment.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public person ID that owns the assignment.", required = true, example = "person-123")
            @PathVariable String personId,
            @Parameter(description = "Assignment ID to remove.", required = true, example = "assignment-123")
            @PathVariable String assignmentId,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("Remove classification assignment request company={} person={} assignment={} actor={}", companyId, personId, assignmentId, actorId);
        assignmentService.removeAssignment(companyId, personId, assignmentId, actorId);
        LOG.info("Removed classification assignment={} for person={}", assignmentId, personId);
    }
}
