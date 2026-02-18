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

    public PersonResponse createPerson(Long companyId, PersonCreateRequest request, String actorId) {
        validateCompany(request.getCompanyId(), companyId);
        String actor = currentActor.subjectOrSystem();
        Person person = personMapper.toEntity(request);
        person.setCompanyId(companyId);
        person.getAudit().setCreatedBy(actor);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse updatePerson(Long companyId, Long personId, PersonUpdateRequest request, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = personRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(personId, companyId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        personMapper.updateEntity(request, person);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse trashPerson(Long companyId, Long personId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = personRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(personId, companyId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        person.getAudit().setTrashedAt(LocalDateTime.now());
        person.getAudit().setTrashedBy(actor);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    public PersonResponse restorePerson(Long companyId, Long personId, String actorId) {
        String actor = currentActor.subjectOrSystem();
        Person person = personRepository.findByIdAndCompanyId(personId, companyId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        person.getAudit().setTrashedAt(null);
        person.getAudit().setTrashedBy(null);
        person.getAudit().setModifiedBy(actor);
        return personMapper.toResponse(personRepository.save(person));
    }

    private void validateCompany(Long bodyCompanyId, Long pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }

}
