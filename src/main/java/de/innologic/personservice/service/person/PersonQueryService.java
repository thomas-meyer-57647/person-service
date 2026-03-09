package de.innologic.personservice.service.person;

import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.mapper.PersonMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.web.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonQueryService.class);

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonQueryService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public Page<PersonResponse> listPersons(String companyId, String q, boolean includeTrashed, Pageable pageable) {
        LOG.info("Listing persons company={} includeTrashed={} query={}", companyId, includeTrashed, q);
        Page<PersonResponse> persons = personRepository.search(companyId, normalize(q), includeTrashed, pageable)
                .map(personMapper::toResponse);
        LOG.info("Found {} persons for company={} includeTrashed={}", persons.getNumberOfElements(), companyId, includeTrashed);
        return persons;
    }

    public PersonResponse getPerson(String companyId, String personId) {
        LOG.info("Fetching person company={} person={}", companyId, personId);
        PersonResponse response = personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .map(personMapper::toResponse)
                .orElseThrow(() -> {
                    LOG.warn("Person not found for company={} person={}", companyId, personId);
                    return new NotFoundException("Person not found for company and id.");
                });
        LOG.info("Found personId={} for company={}", response.getPersonId(), companyId);
        return response;
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}

