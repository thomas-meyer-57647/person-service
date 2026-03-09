package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonCommunicationRefsRequest;
import de.innologic.personservice.dto.PersonCommunicationRefsResponse;
import de.innologic.personservice.service.person.PersonCommunicationRefService;
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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/persons/{personId}/communication-refs")
@Tag(name = "Person Communication Refs")
@SecurityRequirement(name = "bearerAuth")
public class PersonCommunicationRefController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonCommunicationRefController.class);

    private final PersonCommunicationRefService personCommunicationRefService;

    public PersonCommunicationRefController(PersonCommunicationRefService personCommunicationRefService) {
        this.personCommunicationRefService = personCommunicationRefService;
    }

    @GetMapping
    @Operation(
            summary = "Get communication refs for person",
            description = "Return the communication reference IDs (contactOwnerType=PERSON/contactOwnerId) that are currently assigned to the requested person."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Communication refs found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonCommunicationRefsResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public PersonCommunicationRefsResponse getCommunicationRefs(
            @Parameter(description = "Tenant (company) ID that scopes the person.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public person ID whose refs should be returned.", required = true, example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123")
            @PathVariable String personId
    ) {
        LOG.info("Get communication refs request company={} person={}", companyId, personId);
        PersonCommunicationRefsResponse response = personCommunicationRefService.getRefs(companyId, personId);
        int count = response.getCommunicationIds() == null ? 0 : response.getCommunicationIds().size();
        LOG.info("Returning {} communication refs for person={} company={}", count, personId, companyId);
        return response;
    }

    @PutMapping
    @Operation(
            summary = "Replace communication refs for person",
            description = "Overwrite the list of contact-service references that should stay docked with this person. The request must include the full desired set."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Communication refs replaced.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonCommunicationRefsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed (missing or invalid refs).",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Person not found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:write')")
    public PersonCommunicationRefsResponse replaceCommunicationRefs(
            @Parameter(description = "Tenant (company) ID that scopes the person.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public person ID whose refs should be replaced.", required = true, example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123")
            @PathVariable String personId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requested full list of communication reference IDs.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonCommunicationRefsRequest.class))
            )
            @Valid @RequestBody PersonCommunicationRefsRequest request,
            @Parameter(description = "Optional actor identifier for auditing.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        List<String> communicationIds = request.getCommunicationIds();
        int requestedCount = communicationIds == null ? 0 : communicationIds.size();
        LOG.info("Replace communication refs request company={} person={} actor={} desiredCount={}", companyId, personId, actorId, requestedCount);
        PersonCommunicationRefsResponse response = personCommunicationRefService.replaceRefs(companyId, personId, communicationIds, actorId);
        int updatedCount = response.getCommunicationIds() == null ? 0 : response.getCommunicationIds().size();
        LOG.info("Replaced {} communication refs for person={} company={}", updatedCount, personId, companyId);
        return response;
    }
}

