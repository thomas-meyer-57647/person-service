package de.innologic.personservice.service.person;

import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.mapper.PersonMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.web.error.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonQueryService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonQueryService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Page<PersonResponse> listPersons(String companyId, String q, boolean includeTrashed, Pageable pageable) {
        return personRepository.search(companyId, normalize(q), includeTrashed, pageable).map(personMapper::toResponse);
    }

    public PersonResponse getPerson(String companyId, String personId) {
        return personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .map(personMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}

