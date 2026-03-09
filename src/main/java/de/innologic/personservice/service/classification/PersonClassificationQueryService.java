package de.innologic.personservice.service.classification;

import de.innologic.personservice.domain.PersonClassification;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.mapper.PersonClassificationMapper;
import de.innologic.personservice.repository.PersonClassificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PersonClassificationQueryService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonClassificationQueryService.class);

    private final PersonClassificationRepository classificationRepository;
    private final PersonClassificationMapper classificationMapper;

    public PersonClassificationQueryService(PersonClassificationRepository classificationRepository, PersonClassificationMapper classificationMapper) {
        this.classificationRepository = classificationRepository;
        this.classificationMapper = classificationMapper;
    }

    public List<PersonClassificationResponse> listClassifications(String companyId, boolean includeInactive) {
        LOG.info("Listing classifications company={} includeInactive={}", companyId, includeInactive);
        List<PersonClassificationResponse> responses = classificationRepository
                .findAllByCompanyIdAndAudit_TrashedAtIsNullOrderByLabelAsc(companyId)
                .stream()
                .filter(classification -> includeInactive || Boolean.TRUE.equals(classification.getActive()))
                .map(classificationMapper::toResponse)
                .collect(Collectors.toList());
        LOG.info("Found {} classifications for company={}", responses.size(), companyId);
        return responses;
    }
}
