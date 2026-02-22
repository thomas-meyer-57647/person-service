package de.innologic.personservice.repository;

import de.innologic.personservice.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    @Query("select t from Team t where t.audit.trashedAt is null")
    List<Team> findAll();

    @Override
    @Query("select t from Team t where t.id = :id and t.audit.trashedAt is null")
    Optional<Team> findById(@Param("id") Long id);

    List<Team> findAllByCompanyIdAndAudit_TrashedAtIsNull(String companyId);

    Optional<Team> findByIdAndCompanyIdAndAudit_TrashedAtIsNull(Long id, String companyId);

    Optional<Team> findByIdAndCompanyId(Long id, String companyId);

    Optional<Team> findByCompanyIdAndNameAndAudit_TrashedAtIsNull(String companyId, String name);

    @Query("""
            select t from Team t
            where t.companyId = :companyId
              and (:includeTrashed = true or t.audit.trashedAt is null)
              and (
                    :q is null
                    or lower(coalesce(t.name, '')) like lower(concat('%', :q, '%'))
                    or lower(coalesce(t.description, '')) like lower(concat('%', :q, '%'))
                  )
            """)
    Page<Team> search(
            @Param("companyId") String companyId,
            @Param("q") String q,
            @Param("includeTrashed") boolean includeTrashed,
            Pageable pageable
    );
}

