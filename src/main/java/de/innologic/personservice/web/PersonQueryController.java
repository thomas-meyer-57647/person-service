package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.service.person.PersonQueryService;
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

@RestController
@RequestMapping("/api/v1/companies/{companyId}/persons")
@Tag(name = "Persons Query")
public class PersonQueryController {

    private final PersonQueryService personQueryService;

    public PersonQueryController(PersonQueryService personQueryService) {
        this.personQueryService = personQueryService;
    }

    @GetMapping
    @Operation(summary = "List persons by company")
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public Page<PersonResponse> listPersons(
            @PathVariable Long companyId,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "false") boolean includeTrashed,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return personQueryService.listPersons(companyId, q, includeTrashed, pageable);
    }

    @GetMapping("/{personId}")
    @Operation(summary = "Get person by id")
    @PreAuthorize("hasAuthority('SCOPE_person:read')")
    public PersonResponse getPerson(@PathVariable Long companyId, @PathVariable Long personId) {
        return personQueryService.getPerson(companyId, personId);
    }
}
