package de.innologic.personservice.repository;

import de.innologic.personservice.domain.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    @Override
    @Query("select tm from TeamMember tm where tm.audit.trashedAt is null")
    List<TeamMember> findAll();

    @Override
    @Query("select tm from TeamMember tm where tm.id = :id and tm.audit.trashedAt is null")
    Optional<TeamMember> findById(@Param("id") Long id);

    List<TeamMember> findAllByCompanyIdAndAudit_TrashedAtIsNull(String companyId);

    List<TeamMember> findAllByTeam_IdAndAudit_TrashedAtIsNull(Long teamId);

    List<TeamMember> findAllByCompanyIdAndTeam_IdAndAudit_TrashedAtIsNull(String companyId, Long teamId);

    List<TeamMember> findAllByPerson_IdAndAudit_TrashedAtIsNull(Long personId);

    Optional<TeamMember> findByTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(Long teamId, Long personId);

    Optional<TeamMember> findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(String companyId, Long teamId, Long personId);
}

