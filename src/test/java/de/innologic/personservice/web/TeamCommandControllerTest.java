package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import de.innologic.personservice.service.team.TeamCommandService;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamCommandController.class)
@Import(GlobalExceptionHandler.class)
class TeamCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamCommandService teamCommandService;

    @Test
    void shouldCreateTeam_whenValidRequest() throws Exception {
        when(teamCommandService.createTeam(eq(1L), any(TeamCreateRequest.class), eq("actor-1")))
                .thenReturn(sampleTeamResponse(20L, 1L));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", 1L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 1,
                                  "name": "Core Team",
                                  "description": "Main team"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }

    @Test
    void shouldReturn409_whenCreateTeamHasDuplicateName() throws Exception {
        when(teamCommandService.createTeam(eq(1L), any(TeamCreateRequest.class), eq("actor-1")))
                .thenThrow(new ConflictException("Team name already exists"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", 1L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 1,
                                  "name": "Core Team"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value(containsString("Team name already exists")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams"));
    }

    @Test
    void shouldUpdateTeam_whenValidPatch() throws Exception {
        when(teamCommandService.updateTeam(eq(1L), eq(20L), any(TeamUpdateRequest.class), eq("actor-1")))
                .thenReturn(sampleTeamResponse(20L, 1L));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", 1L, 20L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "Updated description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenUpdateTeamNotFound() throws Exception {
        when(teamCommandService.updateTeam(eq(1L), eq(404L), any(TeamUpdateRequest.class), eq("actor-1")))
                .thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", 1L, 404L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "Updated description"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/404"));
    }

    @Test
    void shouldTrashTeam_whenTeamExists() throws Exception {
        TeamResponse trashed = sampleTeamResponse(20L, 1L);
        trashed.setTrashedAt(LocalDateTime.now());
        trashed.setTrashedBy("actor-1");
        when(teamCommandService.trashTeam(1L, 20L, "actor-1")).thenReturn(trashed);

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", 1L, 20L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.trashedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenTrashTeamNotFound() throws Exception {
        when(teamCommandService.trashTeam(1L, 404L, "actor-1")).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", 1L, 404L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/404/trash"));
    }

    @Test
    void shouldRestoreTeam_whenTeamExists() throws Exception {
        TeamResponse restored = sampleTeamResponse(20L, 1L);
        restored.setTrashedAt(null);
        restored.setTrashedBy(null);
        when(teamCommandService.restoreTeam(1L, 20L, "actor-1")).thenReturn(restored);

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", 1L, 20L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.trashedAt").isEmpty());
    }

    @Test
    void shouldReturn404_whenRestoreTeamNotFound() throws Exception {
        when(teamCommandService.restoreTeam(1L, 404L, "actor-1")).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", 1L, 404L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/404/restore"));
    }

    @Test
    void shouldAddMember_whenValidRequest() throws Exception {
        TeamMemberResponse response = sampleMemberResponse(50L, 1L, 20L, 10L);
        when(teamCommandService.addTeamMember(eq(1L), eq(20L), any(TeamMemberAddRequest.class), eq("actor-1")))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", 1L, 20L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "personId": 10,
                                  "role": "Developer"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(50))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.teamId").value(20))
                .andExpect(jsonPath("$.personId").value(10));
    }

    @Test
    void shouldReturn409_whenAddMemberDuplicate() throws Exception {
        when(teamCommandService.addTeamMember(eq(1L), eq(20L), any(TeamMemberAddRequest.class), eq("actor-1")))
                .thenThrow(new ConflictException("Person is already an active member"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", 1L, 20L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "personId": 10,
                                  "role": "Developer"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("CONFLICT"))
                .andExpect(jsonPath("$.message").value(containsString("already an active member")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/20/members"));
    }

    @Test
    void shouldRemoveMember_whenExistingMembership() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", 1L, 20L, 10L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404_whenRemoveMemberNotFound() throws Exception {
        doThrow(new NotFoundException("Active team member relation not found"))
                .when(teamCommandService).removeTeamMember(1L, 20L, 404L, "actor-1");

        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", 1L, 20L, 404L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Active team member relation not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/20/members/404"));
    }

    private TeamResponse sampleTeamResponse(Long id, Long companyId) {
        TeamResponse response = new TeamResponse();
        response.setId(id);
        response.setCompanyId(companyId);
        response.setName("Core Team");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("actor-1");
        response.setModifiedBy("actor-1");
        return response;
    }

    private TeamMemberResponse sampleMemberResponse(Long id, Long companyId, Long teamId, Long personId) {
        TeamMemberResponse response = new TeamMemberResponse();
        response.setId(id);
        response.setCompanyId(companyId);
        response.setTeamId(teamId);
        response.setPersonId(personId);
        response.setRole("Developer");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("actor-1");
        response.setModifiedBy("actor-1");
        return response;
    }
}

