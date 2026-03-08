package de.innologic.personservice.service.team;

import de.innologic.personservice.domain.Person;
import de.innologic.personservice.domain.Team;
import de.innologic.personservice.domain.TeamMember;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.mapper.TeamMapper;
import de.innologic.personservice.mapper.TeamMemberMapper;
import de.innologic.personservice.repository.PersonRepository;
import de.innologic.personservice.repository.TeamMemberRepository;
import de.innologic.personservice.repository.TeamRepository;
import de.innologic.personservice.security.CurrentActor;
import de.innologic.personservice.web.error.BadRequestException;
import de.innologic.personservice.web.error.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamCommandServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private TeamMemberRepository teamMemberRepository;

    @Mock
    private TeamMapper teamMapper;

    @Mock
    private TeamMemberMapper teamMemberMapper;

    @Mock
    private CurrentActor currentActor;

    private TeamCommandService teamCommandService;

    @BeforeEach
    void setUp() {
        teamCommandService = new TeamCommandService(
                teamRepository,
                personRepository,
                teamMemberRepository,
                teamMapper,
                teamMemberMapper,
                currentActor
        );
    }

    @Test
    void shouldReturnResponse_whenPrimaryRuleNotViolated() {
        Team team = new Team();
        Person person = new Person();
        person.setCompanyId("company-1");
        person.setPublicId("person-10");
        TeamMemberAddRequest request = new TeamMemberAddRequest();
        request.setPersonId("person-10");
        TeamMember teamMember = new TeamMember();
        TeamMemberResponse response = new TeamMemberResponse();
        response.setMembershipId("membership-10");

        when(teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull("team-20", "company-1"))
                .thenReturn(Optional.of(team));
        when(personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull("company-1", "person-10"))
                .thenReturn(Optional.of(person));
        when(teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(eq("company-1"), any(), any()))
                .thenReturn(Optional.empty());
        when(teamMemberMapper.toEntity(any(TeamMemberAddRequest.class))).thenReturn(teamMember);
        when(teamMemberRepository.save(teamMember)).thenReturn(teamMember);
        when(teamMemberMapper.toResponse(teamMember)).thenReturn(response);
        when(currentActor.subjectOrSystem()).thenReturn("actor-1");

        TeamMemberResponse actual = teamCommandService.addTeamMember("company-1", "team-20", request, null);

        assertSame(response, actual);
    }

    @Test
    void shouldThrowConflict_whenPrimaryMembershipAlreadyExists() {
        Team team = new Team();
        Person person = new Person();
        person.setCompanyId("company-1");
        person.setPublicId("person-10");
        TeamMemberAddRequest request = new TeamMemberAddRequest();
        request.setPersonId("person-10");
        request.setIsPrimary(Boolean.TRUE);

        when(teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull("team-20", "company-1"))
                .thenReturn(Optional.of(team));
        when(personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull("company-1", "person-10"))
                .thenReturn(Optional.of(person));
        when(teamMemberRepository.findByCompanyIdAndPerson_IdAndIsPrimaryTrueAndLeftAtIsNullAndAudit_TrashedAtIsNull(eq("company-1"), any()))
                .thenReturn(Optional.of(new TeamMember()));

        assertThrows(ConflictException.class, () ->
                teamCommandService.addTeamMember("company-1", "team-20", request, null)
        );
    }

    @Test
    void shouldThrowBadRequest_whenJoinedAtInFuture() {
        Team team = new Team();
        Person person = new Person();
        person.setCompanyId("company-1");
        person.setPublicId("person-10");
        TeamMemberAddRequest request = new TeamMemberAddRequest();
        request.setPersonId("person-10");
        request.setJoinedAt(LocalDateTime.now().plusDays(1));

        when(teamRepository.findByTeamIdAndCompanyIdAndAudit_TrashedAtIsNull("team-20", "company-1"))
                .thenReturn(Optional.of(team));
        when(personRepository.findByCompanyIdAndPublicIdAndAudit_TrashedAtIsNull("company-1", "person-10"))
                .thenReturn(Optional.of(person));
        when(teamMemberRepository.findByCompanyIdAndTeam_IdAndPerson_IdAndAudit_TrashedAtIsNull(eq("company-1"), any(), any()))
                .thenReturn(Optional.empty());
        when(teamMemberMapper.toEntity(any(TeamMemberAddRequest.class))).thenReturn(new TeamMember());

        assertThrows(BadRequestException.class, () ->
                teamCommandService.addTeamMember("company-1", "team-20", request, null)
        );
    }
}
