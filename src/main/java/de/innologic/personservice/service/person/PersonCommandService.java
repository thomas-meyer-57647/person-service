package de.innologic.personservice.service.person;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.mapper.PersonMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PersonCommandService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final CurrentActor currentActor;

    public PersonCommandService(PersonRepository personRepository, PersonMapper personMapper, CurrentActor currentActor) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.currentActor = currentActor;
    }

    public PersonResponse createPerson(String companyId, PersonCreateRequest request, String actorId) {
        validateCompany(request.getCompanyId(), companyId);
        String actor = currentActor.subjectOrSystem();
        Person person = personMapper.toEntity(request);
        person.setCompanyId(companyId);
        if (person.getPublicId() == null || person.getPublicId().isBlank()) {
            person.setPublicId(UUID.randomUUID().toString());
        }
        person.getAudit().setCreatedBy(actor);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse updatePerson(String companyId, String personId, PersonUpdateRequest request, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = findActiveByPublicId(companyId, personId);
        personMapper.updateEntity(request, person);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse trashPerson(String companyId, String personId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = findActiveByPublicId(companyId, personId);
        person.getAudit().setTrashedAt(LocalDateTime.now());
        person.getAudit().setTrashedBy(actor);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse restorePerson(String companyId, String personId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = personRepository.findByCompanyIdAndPublicId(companyId, personId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        person.getAudit().setTrashedAt(null);
        person.getAudit().setTrashedBy(null);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    private void validateCompany(String bodyCompanyId, String pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }

    private Person findActiveByPublicId(String companyId, String personId) {
        return personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
    }

}

