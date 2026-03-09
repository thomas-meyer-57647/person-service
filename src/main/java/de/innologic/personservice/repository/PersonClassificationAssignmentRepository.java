package de.innologic.personservice.repository;

import de.innologic.personservice.domain.PersonClassificationAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonClassificationAssignmentRepository extends JpaRepository<PersonClassificationAssignment, Long> {

    Optional<PersonClassificationAssignment> findByCompanyIdAndAssignmentIdAndAudit_TrashedAtIsNull(String companyId, String assignmentId);
}
