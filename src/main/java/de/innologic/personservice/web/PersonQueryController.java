package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.service.person.PersonQueryService;
import de.innologic.personservice.web.error.ProblemDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/persons")
@Tag(name = "Persons Query")
@SecurityRequirement(name = "bearerAuth")
public class PersonQueryController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonQueryController.class);

    private final PersonQueryService personQueryService;

    public PersonQueryController(PersonQueryService personQueryService) {
        this.personQueryService = personQueryService;
    }

    @GetMapping
    @Operation(summary = "List persons by company",
            description = "Return a paged view of tenant-scoped persons. Contact docking metadata (contactOwnerType=PERSON, contactOwnerId=personId) is supplied for every record so downstream services can load the associated contact points.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Page of persons.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameter or pagination argument.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public Page<PersonResponse> listPersons(
            @Parameter(description = "Tenant (company) ID from the path and JWT tenant claim.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Optional search term evaluated against displayName, firstName, lastName and notes.", example = "Mustermann")
            @RequestParam(required = false) String q,
            @Parameter(description = "When true, include trashed persons in the response (default false).", example = "false")
            @RequestParam(defaultValue = "false") boolean includeTrashed,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        LOG.info("List persons request company={} includeTrashed={} query={}", companyId, includeTrashed, q);
        Page<PersonResponse> persons = personQueryService.listPersons(companyId, q, includeTrashed, pageable);
        LOG.info("Returning {} persons for company={} includeTrashed={} query={}", persons.getNumberOfElements(), companyId, includeTrashed, q);
        return persons;
    }

    @GetMapping("/{personId}")
    @Operation(summary = "Get person by id",
            description = "Retrieve a single person by its public ID. contactOwnerType is always PERSON and contactOwnerId matches the requested personId.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Person found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "404", description = "Person not found or trashed.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public PersonResponse getPerson(
            @Parameter(description = "Tenant (company) ID.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Public person ID to load.", required = true, example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123")
            @PathVariable String personId
    ) {
        LOG.info("Get person request company={} person={}", companyId, personId);
        PersonResponse response = personQueryService.getPerson(companyId, personId);
        LOG.info("Found personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }
}

