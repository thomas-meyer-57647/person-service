package de.innologic.personservice.service.classification;

import de.innologic.personservice.domain.PersonClassification;
import de.innologic.personservice.dto.PersonClassificationCreateRequest;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.dto.PersonClassificationUpdateRequest;
import de.innologic.personservice.mapper.PersonClassificationMapper;
import de.innologic.personservice.repository.PersonClassificationRepository;
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class PersonClassificationCommandService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonClassificationCommandService.class);

    private final PersonClassificationRepository classificationRepository;
    private final PersonClassificationMapper classificationMapper;
    private final CurrentActor currentActor;

    public PersonClassificationCommandService(
            PersonClassificationRepository classificationRepository,
            PersonClassificationMapper classificationMapper,
            CurrentActor currentActor
    ) {
        this.classificationRepository = classificationRepository;
        this.classificationMapper = classificationMapper;
        this.currentActor = currentActor;
    }

    public PersonClassificationResponse createClassification(String companyId, PersonClassificationCreateRequest request, String actorId) {
        LOG.info("Creating classification company={} key={} code={} actor={}", companyId, request.getKey(), request.getCode(), actorId);
        validateCompany(request.getCompanyId(), companyId);
        PersonClassification classification = classificationMapper.toEntity(request);
        classification.setCompanyId(companyId);
        classification.setActive(request.getActive() == null ? Boolean.TRUE : request.getActive());
        classification.setClassificationId(UUID.randomUUID().toString());
        String actor = currentActor.subjectOrSystem();
        classification.getAudit().setCreatedBy(actor);
        classification.getAudit().setModifiedBy(actor);
        ensureUniqueClassification(companyId, classification);
        PersonClassification saved = classificationRepository.save(classification);
        LOG.info("Created classificationId={} for company={}", saved.getClassificationId(), companyId);
        return classificationMapper.toResponse(saved);
    }

    public PersonClassificationResponse updateClassification(String companyId, String classificationId, PersonClassificationUpdateRequest request, String actorId) {
        LOG.info("Updating classification company={} classification={} actor={}", companyId, classificationId, actorId);
        PersonClassification classification = classificationRepository.findByCompanyIdAndClassificationIdAndAudit_TrashedAtIsNull(companyId, classificationId)
                .orElseThrow(() -> new NotFoundException("Classification not found for company and id."));
        classificationMapper.updateEntity(request, classification);
        ensureUniqueClassification(companyId, classification);
        String actor = currentActor.subjectOrSystem();
        classification.getAudit().setModifiedBy(actor);
        PersonClassification saved = classificationRepository.save(classification);
        LOG.info("Updated classificationId={} for company={}", saved.getClassificationId(), companyId);
        return classificationMapper.toResponse(saved);
    }

    public PersonClassificationResponse deactivateClassification(String companyId, String classificationId, String actorId) {
        LOG.info("Deactivating classification company={} classification={} actor={}", companyId, classificationId, actorId);
        PersonClassification classification = classificationRepository.findByCompanyIdAndClassificationIdAndAudit_TrashedAtIsNull(companyId, classificationId)
                .orElseThrow(() -> new NotFoundException("Classification not found for company and id."));
        classification.setActive(false);
        String actor = currentActor.subjectOrSystem();
        classification.getAudit().setModifiedBy(actor);
        PersonClassification saved = classificationRepository.save(classification);
        LOG.info("Deactivated classificationId={} for company={}", saved.getClassificationId(), companyId);
        return classificationMapper.toResponse(saved);
    }

    private void ensureUniqueClassification(String companyId, PersonClassification classification) {
        classificationRepository.findByCompanyIdAndKeyAndCodeAndAudit_TrashedAtIsNull(companyId, classification.getKey(), classification.getCode())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(classification.getId())) {
                        LOG.warn("Classification key/code already exists company={} key={} code={}", companyId, classification.getKey(), classification.getCode());
                        throw new ConflictException("Classification with key and code already exists for this company.");
                    }
                });
    }

    private void validateCompany(String bodyCompanyId, String pathCompanyId) {
        if (bodyCompanyId == null || !bodyCompanyId.equals(pathCompanyId)) {
            LOG.warn("Company mismatch path={} body={}", pathCompanyId, bodyCompanyId);
            throw new BadRequestException("companyId in body must match companyId in path.");
        }
    }
}
