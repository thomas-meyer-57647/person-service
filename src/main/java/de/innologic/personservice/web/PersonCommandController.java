package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.service.person.PersonCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Create person")
    public PersonResponse createPerson(
            @PathVariable Long companyId,
            @Valid @RequestBody PersonCreateRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.createPerson(companyId, request, actorId);
    }

    @PatchMapping("/{personId}")
    @Operation(summary = "Update person")
    public PersonResponse updatePerson(
            @PathVariable Long companyId,
            @PathVariable Long personId,
            @Valid @RequestBody PersonUpdateRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.updatePerson(companyId, personId, request, actorId);
    }

    @PostMapping("/{personId}/trash")
    @Operation(summary = "Trash person")
    public PersonResponse trashPerson(
            @PathVariable Long companyId,
            @PathVariable Long personId,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.trashPerson(companyId, personId, actorId);
    }

    @PostMapping("/{personId}/restore")
    @Operation(summary = "Restore person")
    public PersonResponse restorePerson(
            @PathVariable Long companyId,
            @PathVariable Long personId,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommandService.restorePerson(companyId, personId, actorId);
    }
}
