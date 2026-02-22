package de.innologic.personservice.repository;

import de.innologic.personservice.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @Query("select p from Person p where p.audit.trashedAt is null")
    List<Person> findAll();

    @Override
    @Query("select p from Person p where p.id = :id and p.audit.trashedAt is null")
    Optional<Person> findById(@Param("id") Long id);

    List<Person> findAllByCompanyIdAndAudit_TrashedAtIsNull(String companyId);

    Optional<Person> findByCompanyIdAndPublicId(String companyId, String publicId);

    Optional<Person> findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull(String companyId, String publicId);

    Optional<Person> findByIdAndCompanyIdAndAudit_TrashedAtIsNull(Long id, String companyId);

    Optional<Person> findByIdAndCompanyId(Long id, String companyId);

    @Query("""
            select p from Person p
            where p.companyId = :companyId
              and (:includeTrashed = true or p.audit.trashedAt is null)
              and (
                    :q is null
                    or lower(coalesce(p.displayName, '')) like lower(concat('%', :q, '%'))
                    or lower(coalesce(p.givenName, '')) like lower(concat('%', :q, '%'))
                    or lower(coalesce(p.familyName, '')) like lower(concat('%', :q, '%'))
                    or lower(coalesce(p.email, '')) like lower(concat('%', :q, '%'))
                  )
            """)
    Page<Person> search(
            @Param("companyId") String companyId,
            @Param("q") String q,
            @Param("includeTrashed") boolean includeTrashed,
            Pageable pageable
    );
}

