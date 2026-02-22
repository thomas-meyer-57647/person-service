package de.innologic.personservice.service.person;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.domain.PersonCommunicationRef;
import de.innologic.personservice.dto.PersonCommunicationRefsResponse;
import de.innologic.personservice.repository.PersonCommunicationRefRepository;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonCommunicationRefService {

    private final PersonRepository personRepository;
    private final PersonCommunicationRefRepository personCommunicationRefRepository;
    private final CurrentActor currentActor;

    public PersonCommunicationRefService(
            PersonRepository personRepository,
            PersonCommunicationRefRepository personCommunicationRefRepository,
            CurrentActor currentActor
    ) {
        this.personRepository = personRepository;
        this.personCommunicationRefRepository = personCommunicationRefRepository;
        this.currentActor = currentActor;
    }

    @Transactional(readOnly = true)
    public PersonCommunicationRefsResponse getRefs(String companyId, String personId) {
        Person person = assertPersonInCompany(companyId, personId);
        List<String> refs = personCommunicationRefRepository
                .findAllByCompanyIdAndPerson_IdAndAudit_TrashedAtIsNull(companyId, person.getId())
                .stream()
                .map(PersonCommunicationRef::getCommunicationId)
                .toList();
        return toResponse(companyId, personId, refs);
    }

    @Transactional
    public PersonCommunicationRefsResponse replaceRefs(String companyId, String personId, List<String> communicationIds, String actorId) {
        Person person = assertPersonInCompany(companyId, personId);
        String actor = currentActor.subjectOrSystem();
        LocalDateTime now = LocalDateTime.now();

        Set<String> desired = normalizeDistinct(communicationIds);
        List<PersonCommunicationRef> allRows = personCommunicationRefRepository.findAllByCompanyIdAndPerson_Id(companyId, person.getId());
        Map<String, PersonCommunicationRef> byCommunicationId = allRows.stream()
                .collect(Collectors.toMap(PersonCommunicationRef::getCommunicationId, Function.identity()));

        for (PersonCommunicationRef existing : allRows) {
            boolean shouldExist = desired.contains(existing.getCommunicationId());
            if (shouldExist && existing.getAudit().getTrashedAt() != null) {
                existing.getAudit().setTrashedAt(null);
                existing.getAudit().setTrashedBy(null);
                existing.getAudit().setModifiedBy(actor);
            }
            if (!shouldExist && existing.getAudit().getTrashedAt() == null) {
                existing.getAudit().setTrashedAt(now);
                existing.getAudit().setTrashedBy(actor);
                existing.getAudit().setModifiedBy(actor);
            }
        }

        List<PersonCommunicationRef> toCreate = new ArrayList<>();
        for (String communicationId : desired) {
            if (!byCommunicationId.containsKey(communicationId)) {
                PersonCommunicationRef ref = new PersonCommunicationRef();
                ref.setCompanyId(companyId);
                ref.setPerson(person);
                ref.setCommunicationId(communicationId);
                ref.getAudit().setCreatedBy(actor);
                ref.getAudit().setModifiedBy(actor);
                toCreate.add(ref);
            }
        }

        if (!allRows.isEmpty()) {
            personCommunicationRefRepository.saveAll(allRows);
        }
        if (!toCreate.isEmpty()) {
            personCommunicationRefRepository.saveAll(toCreate);
        }

        return getRefs(companyId, personId);
    }

    private Person assertPersonInCompany(String companyId, String personId) {
        return personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
    }

    private Set<String> normalizeDistinct(List<String> communicationIds) {
        if (communicationIds == null) {
            return Set.of();
        }
        return communicationIds.stream()
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PersonCommunicationRefsResponse toResponse(String companyId, String personId, List<String> refs) {
        PersonCommunicationRefsResponse response = new PersonCommunicationRefsResponse();
        response.setCompanyId(companyId);
        response.setPersonId(personId);
        response.setCommunicationIds(refs);
        return response;
    }

}

