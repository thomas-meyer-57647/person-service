package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.service.classification.PersonClassificationQueryService;
import de.innologic.personservice.web.error.ProblemDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/person-classifications")
@Tag(name = "Person Classifications Query")
@SecurityRequirement(name = "bearerAuth")
public class PersonClassificationQueryController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonClassificationQueryController.class);

    private final PersonClassificationQueryService classificationQueryService;

    public PersonClassificationQueryController(PersonClassificationQueryService classificationQueryService) {
        this.classificationQueryService = classificationQueryService;
    }

    @GetMapping
    @Operation(summary = "List classifications", description = "Return all classifications for the requested tenant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Classifications loaded.",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonClassificationResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "401", description = "Authentication missing or invalid.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class))),
            @ApiResponse(responseCode = "403", description = "Insufficient scope.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailResponse.class)))
    })
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public List<PersonClassificationResponse> listClassifications(
            @Parameter(description = "Tenant (company) ID that scopes the classifications.", required = true, example = "company-123")
            @PathVariable String companyId,
            @Parameter(description = "Include inactive classifications in the response.", example = "false")
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive,
            @Parameter(description = "Optional actor identifier for logging.", example = "actor-1")
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        LOG.info("List classifications request company={} includeInactive={} actor={}", companyId, includeInactive, actorId);
        List<PersonClassificationResponse> response = classificationQueryService.listClassifications(companyId, includeInactive);
        LOG.info("Returning {} classifications for company={}", response.size(), companyId);
        return response;
    }
}
