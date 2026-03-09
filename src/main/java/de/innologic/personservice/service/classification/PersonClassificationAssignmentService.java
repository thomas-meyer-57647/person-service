package de.innologic.personservice.service.classification;

import de.innologic.personservice.domain.PersonClassification;
import de.innologic.personservice.domain.PersonClassificationAssignment;
import de.innologic.personservice.dto.PersonClassificationAssignmentRequest;
import de.innologic.personservice.dto.PersonClassificationAssignmentResponse;
import de.innologic.personservice.mapper.PersonClassificationAssignmentMapper;
import de.innologic.personservice.repository.PersonClassificationAssignmentRepository;
import de.innologic.personservice.repository.PersonClassificationRepository;
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
public class PersonClassificationAssignmentService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonClassificationAssignmentService.class);

    private final PersonClassificationAssignmentRepository assignmentRepository;
    private final PersonClassificationRepository classificationRepository;
    private final PersonRepository personRepository;
    private final PersonClassificationAssignmentMapper assignmentMapper;
    private final CurrentActor currentActor;

    public PersonClassificationAssignmentService(
            PersonClassificationAssignmentRepository assignmentRepository,
            PersonClassificationRepository classificationRepository,
            PersonRepository personRepository,
            PersonClassificationAssignmentMapper assignmentMapper,
            CurrentActor currentActor
    ) {
        this.assignmentRepository = assignmentRepository;
        this.classificationRepository = classificationRepository;
        this.personRepository = personRepository;
        this.assignmentMapper = assignmentMapper;
        this.currentActor = currentActor;
    }

    public PersonClassificationAssignmentResponse assignClassification(String companyId, String personId, PersonClassificationAssignmentRequest request, String actorId) {
        LOG.info("Assigning classification={} to person={} company={} actor={}", request.getClassificationId(), personId, companyId, actorId);
        PersonClassification classification = classificationRepository.findByCompanyIdAndClassificationIdAndAudit_TrashedAtIsNull(companyId, request.getClassificationId())
                .orElseThrow(() -> new NotFoundException("Classification not found for company and id."));
        if (!Boolean.TRUE.equals(classification.getActive())) {
            LOG.warn("Classification {} is not active for company={}", request.getClassificationId(), companyId);
            throw new BadRequestException("Classification is not active and cannot be assigned.");
        }
        personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(companyId, personId)
                .orElseThrow(() -> new NotFoundException("Person not found for company and id."));
        PersonClassificationAssignment assignment = assignmentMapper.toEntity(request);
        assignment.setCompanyId(companyId);
        assignment.setPersonId(personId);
        assignment.setClassificationId(request.getClassificationId());
        assignment.setAssignmentId(UUID.randomUUID().toString());
        assignment.setIsPrimary(Boolean.TRUE.equals(request.getIsPrimary()));
        if (assignment.getValidFrom() == null) {
            assignment.setValidFrom(LocalDateTime.now());
        }
        if (assignment.getValidTo() != null && assignment.getValidTo().isBefore(assignment.getValidFrom())) {
            throw new BadRequestException("validTo cannot be before validFrom.");
        }
        String actor = currentActor.subjectOrSystem();
        assignment.getAudit().setCreatedBy(actor);
        assignment.getAudit().setModifiedBy(actor);
        PersonClassificationAssignment saved = assignmentRepository.save(assignment);
        LOG.info("Created classification assignment={} person={} classification={}", saved.getAssignmentId(), personId, saved.getClassificationId());
        return assignmentMapper.toResponse(saved);
    }

    public void removeAssignment(String companyId, String personId, String assignmentId, String actorId) {
        LOG.info("Removing classification assignment={} person={} company={} actor={}", assignmentId, personId, companyId, actorId);
        PersonClassificationAssignment assignment = assignmentRepository.findByCompanyIdAndAssignmentIdAndAudit_TrashedAtIsNull(companyId, assignmentId)
                .orElseThrow(() -> new NotFoundException("Classification assignment not found for company and id."));
        if (!assignment.getPersonId().equals(personId)) {
            LOG.warn("Assignment {} does not belong to person {} company={}", assignmentId, personId, companyId);
            throw new NotFoundException("Classification assignment not found.");
        }
        String actor = currentActor.subjectOrSystem();
        assignment.getAudit().setTrashedAt(LocalDateTime.now());
        assignment.getAudit().setTrashedBy(actor);
        assignment.getAudit().setModifiedBy(actor);
        if (assignment.getValidTo() == null) {
            assignment.setValidTo(LocalDateTime.now());
        }
        assignmentRepository.save(assignment);
        LOG.info("Trashed classification assignment={} for person={} classification={}", assignmentId, personId, assignment.getClassificationId());
    }
}
