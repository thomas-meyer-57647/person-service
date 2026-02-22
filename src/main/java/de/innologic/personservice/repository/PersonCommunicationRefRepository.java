package de.innologic.personservice.repository;

import de.innologic.personservice.domain.PersonCommunicationRef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonCommunicationRefRepository extends JpaRepository<PersonCommunicationRef, Long> {

    List<PersonCommunicationRef> findAllByCompanyIdAndPerson_IdAndAudit_TrashedAtIsNull(String companyId, Long personId);

    List<PersonCommunicationRef> findAllByCompanyIdAndPerson_Id(String companyId, Long personId);
}

