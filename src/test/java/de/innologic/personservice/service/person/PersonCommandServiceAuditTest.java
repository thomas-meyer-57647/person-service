package de.innologic.personservice.service.person;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.mapper.PersonMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.security.CurrentActor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonCommandServiceAuditTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    private PersonCommandService personCommandService;

    @BeforeEach
    void setUp() {
        personCommandService = new PersonCommandService(personRepository, personMapper, new CurrentActor());
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createPerson_shouldSetCreatedAndModifiedByFromJwtSub() {
        setJwtSubject("user-123");

        PersonCreateRequest request = new PersonCreateRequest();
        request.setCompanyId(1L);
        request.setDisplayName("Max Mustermann");

        Person person = new Person();
        when(personMapper.toEntity(request)).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(personMapper.toResponse(any(Person.class))).thenAnswer(invocation -> {
            Person saved = invocation.getArgument(0);
            PersonResponse response = new PersonResponse();
            response.setCreatedBy(saved.getAudit().getCreatedBy());
            response.setModifiedBy(saved.getAudit().getModifiedBy());
            return response;
        });

        PersonResponse result = personCommandService.createPerson(1L, request, "ignored-header-actor");

        assertThat(result.getCreatedBy()).isEqualTo("user-123");
        assertThat(result.getModifiedBy()).isEqualTo("user-123");
    }

    @Test
    void updatePerson_shouldSetModifiedByFromJwtSub() {
        setJwtSubject("user-456");

        Person person = new Person();
        person.setCompanyId(1L);
        when(personRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(10L, 1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(personMapper.toResponse(any(Person.class))).thenReturn(new PersonResponse());

        personCommandService.updatePerson(1L, 10L, new PersonUpdateRequest(), "ignored-header-actor");

        assertThat(person.getAudit().getModifiedBy()).isEqualTo("user-456");
    }

    @Test
    void trashPerson_shouldSetModifiedAndTrashedByFromJwtSub() {
        setJwtSubject("user-789");

        Person person = new Person();
        person.setCompanyId(1L);
        when(personRepository.findByIdAndCompanyIdAndAudit_TrashedAtIsNull(eq(10L), eq(1L))).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(personMapper.toResponse(any(Person.class))).thenReturn(new PersonResponse());

        personCommandService.trashPerson(1L, 10L, "ignored-header-actor");

        assertThat(person.getAudit().getModifiedBy()).isEqualTo("user-789");
        assertThat(person.getAudit().getTrashedBy()).isEqualTo("user-789");
    }

    private void setJwtSubject(String subject) {
        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "none")
                .subject(subject)
                .build();
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));
    }
}
