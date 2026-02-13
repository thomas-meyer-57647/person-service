package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonCommunicationRefsRequest;
import de.innologic.personservice.dto.PersonCommunicationRefsResponse;
import de.innologic.personservice.service.person.PersonCommunicationRefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
public class PersonCommunicationRefController {

    private final PersonCommunicationRefService personCommunicationRefService;

    public PersonCommunicationRefController(PersonCommunicationRefService personCommunicationRefService) {
        this.personCommunicationRefService = personCommunicationRefService;
    }

    @GetMapping
    @Operation(summary = "Get communication refs for person")
    public PersonCommunicationRefsResponse getCommunicationRefs(
            @PathVariable Long companyId,
            @PathVariable Long personId
    ) {
        return personCommunicationRefService.getRefs(companyId, personId);
    }

    @PutMapping
    @Operation(summary = "Replace communication refs for person")
    public PersonCommunicationRefsResponse replaceCommunicationRefs(
            @PathVariable Long companyId,
            @PathVariable Long personId,
            @Valid @RequestBody PersonCommunicationRefsRequest request,
            @RequestHeader(name = "X-Actor-Id", required = false) String actorId
    ) {
        return personCommunicationRefService.replaceRefs(companyId, personId, request.getCommunicationIds(), actorId);
    }
}
