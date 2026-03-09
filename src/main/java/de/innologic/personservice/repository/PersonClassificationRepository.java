package de.innologic.personservice.repository;

import de.innologic.personservice.domain.PersonClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonClassificationRepository extends JpaRepository<PersonClassification, Long> {

    Optional<PersonClassification> findByCompanyIdAndClassificationIdAndAudit_TrashedAtIsNull(String companyId, String classificationId);

    Optional<PersonClassification> findByCompanyIdAndKeyAndCodeAndAudit_TrashedAtIsNull(String companyId, String key, String code);

    List<PersonClassification> findAllByCompanyIdAndAudit_TrashedAtIsNullOrderByLabelAsc(String companyId);
}
