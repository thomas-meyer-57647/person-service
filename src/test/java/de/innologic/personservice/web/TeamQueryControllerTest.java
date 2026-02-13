package de.innologic.personservice.web;

import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.service.team.TeamQueryService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeamQueryController.class)
@Import(GlobalExceptionHandler.class)
class TeamQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamQueryService teamQueryService;

    @Test
    void shouldListTeams_whenValidRequest() throws Exception {
        when(teamQueryService.listTeams(eq(1L), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(sampleTeamResponse(20L, 1L)), PageRequest.of(0, 20), 1));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", 1L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.content[0].id").value(20))
                .andExpect(jsonPath("$.content[0].companyId").value(1))
                .andExpect(jsonPath("$.content[0].createdAt").isNotEmpty());
    }

    @Test
    void shouldReturn400_whenInvalidIncludeTrashedForTeamList() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", 1L)
                        .param("includeTrashed", "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("INVALID_PARAMETER"))
                .andExpect(jsonPath("$.message").value(containsString("Invalid request parameter")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams"));
    }

    @Test
    void shouldGetTeam_whenExists() throws Exception {
        when(teamQueryService.getTeam(1L, 20L)).thenReturn(sampleTeamResponse(20L, 1L));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", 1L, 20L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenTeamNotFound() throws Exception {
        when(teamQueryService.getTeam(1L, 404L)).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", 1L, 404L))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/404"));
    }

    @Test
    void shouldGetTeamMembers_whenTeamExists() throws Exception {
        TeamMemberResponse member = new TeamMemberResponse();
        member.setId(99L);
        member.setCompanyId(1L);
        member.setTeamId(20L);
        member.setPersonId(10L);
        member.setCreatedAt(LocalDateTime.now().minusDays(1));
        member.setModifiedAt(LocalDateTime.now());
        when(teamQueryService.getTeamMembers(1L, 20L)).thenReturn(List.of(member));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", 1L, 20L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$[0].id").value(99))
                .andExpect(jsonPath("$[0].companyId").value(1))
                .andExpect(jsonPath("$[0].teamId").value(20))
                .andExpect(jsonPath("$[0].personId").value(10));
    }

    @Test
    void shouldReturn404_whenGetTeamMembersTeamNotFound() throws Exception {
        when(teamQueryService.getTeamMembers(1L, 404L)).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", 1L, 404L))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/404/members"));
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
}

