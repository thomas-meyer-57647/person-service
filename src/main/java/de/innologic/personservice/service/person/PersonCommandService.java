package de.innologic.personservice.service.person;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.domain.PersonStatus;
import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.mapper.PersonMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PersonCommandService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonCommandService.class);

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final CurrentActor currentActor;

    public PersonCommandService(PersonRepository personRepository, PersonMapper personMapper, CurrentActor currentActor) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.currentActor = currentActor;
    }

    public PersonResponse createPerson(String companyId, PersonCreateRequest request, String actorId) {
        LOG.info("Creating person request company={} actor={}", companyId, actorId);
        validateCompany(request.getCompanyId(), companyId);
        String actor = currentActor.subjectOrSystem();
        Person person = personMapper.toEntity(request);
        person.setCompanyId(companyId);
        person.setStatus(PersonStatus.ACTIVE);
        if (person.getPublicId() == null || person.getPublicId().isBlank()) {
            person.setPublicId(UUID.randomUUID().toString());
        }
        person.getAudit().setCreatedBy(actor);
        person.getAudit().setModifiedBy(actor);
        Person saved = personRepository.save(person);
        PersonResponse response = personMapper.toResponse(saved);
        LOG.info("Created personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }

    public PersonResponse updatePerson(String companyId, String personId, PersonUpdateRequest request, String actorId) {
        LOG.info("Updating person request company={} person={} actor={}", companyId, personId, actorId);
        String actor = currentActor.subjectOrSystem();
        Person person = findActiveByPublicId(companyId, personId);
        personMapper.updateEntity(request, person);
        person.getAudit().setModifiedBy(actor);
        Person saved = personRepository.save(person);
        PersonResponse response = personMapper.toResponse(saved);
        LOG.info("Updated personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }

    public PersonResponse trashPerson(String companyId, String personId, String actorId) {
        LOG.info("Trashing person request company={} person={} actor={}", companyId, personId, actorId);
        String actor = currentActor.subjectOrSystem();
        Person person = findActiveByPublicId(companyId, personId);
        person.setStatus(PersonStatus.TRASHED);
        person.getAudit().setTrashedAt(LocalDateTime.now());
        person.getAudit().setTrashedBy(actor);
        person.getAudit().setModifiedBy(actor);
        Person saved = personRepository.save(person);
        PersonResponse response = personMapper.toResponse(saved);
        LOG.info("Trashed personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }

    public PersonResponse restorePerson(String companyId, String personId, String actorId) {
        LOG.info("Restoring person request company={} person={} actor={}", companyId, personId, actorId);
        String actor = currentActor.subjectOrSystem();
        Person person = personRepository.findByCompanyIdAndPublicId(companyId, personId)
                .orElseThrow(() -> {
                    LOG.warn("Cannot restore missing person company={} person={}", companyId, personId);
                    return new NotFoundException("Person not found for company and id.");
                });
        person.setStatus(PersonStatus.ACTIVE);
        person.getAudit().setTrashedAt(null);
        person.getAudit().setTrashedBy(null);
        person.getAudit().setModifiedBy(actor);
        Person saved = personRepository.save(person);
        PersonResponse response = personMapper.toResponse(saved);
        LOG.info("Restored personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }

    private void validateCompany(String bodyCompanyId, String pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            LOG.warn("Company mismatch path={} body={}", pathCompanyId, bodyCompanyId);
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }

    private Person findActiveByPublicId(String companyId, String personId) {
        return personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .orElseThrow(() -> {
                    LOG.warn("Active person not found for company={} person={}", companyId, personId);
                    return new NotFoundException("Person not found for company and id.");
                });
    }

}

