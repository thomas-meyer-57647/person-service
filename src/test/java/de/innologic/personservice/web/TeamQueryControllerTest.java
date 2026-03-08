package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
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
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class})
class TeamQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamQueryService teamQueryService;

    @Test
    void shouldListTeams_whenValidRequest() throws Exception {
        when(teamQueryService.listTeams(eq("1"), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(sampleTeamResponse("team-20", "1")), PageRequest.of(0, 20), 1));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", "1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.content[0].teamId").value("team-20"))
                .andExpect(jsonPath("$.content[0].companyId").value("1"))
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
        when(teamQueryService.getTeam("1", "team-20")).thenReturn(sampleTeamResponse("team-20", "1"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", "1", "team-20"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.teamId").value("team-20"))
                .andExpect(jsonPath("$.companyId").value("1"))
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenTeamNotFound() throws Exception {
        when(teamQueryService.getTeam("1", "team-404")).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", "1", "team-404"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/team-404"));
    }

    @Test
    void shouldGetTeamMembers_whenTeamExists() throws Exception {
        TeamMemberResponse member = new TeamMemberResponse();
        member.setMembershipId("membership-99");
        member.setCompanyId("1");
        member.setTeamId("team-20");
        member.setPersonId("person-10");
        member.setCreatedAt(LocalDateTime.now().minusDays(1));
        member.setModifiedAt(LocalDateTime.now());
        member.setIsPrimary(Boolean.TRUE);
        when(teamQueryService.getTeamMembers("1", "team-20")).thenReturn(List.of(member));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", "1", "team-20"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$[0].membershipId").value("membership-99"))
                .andExpect(jsonPath("$[0].companyId").value("1"))
                .andExpect(jsonPath("$[0].teamId").value("team-20"))
                .andExpect(jsonPath("$[0].personId").value("person-10"));
    }

    @Test
    void shouldReturn404_whenGetTeamMembersTeamNotFound() throws Exception {
        when(teamQueryService.getTeamMembers("1", "team-404")).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", "1", "team-404"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Team not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/teams/team-404/members"));
    }

    private TeamResponse sampleTeamResponse(String teamId, String companyId) {
        TeamResponse response = new TeamResponse();
        response.setCompanyId(companyId);
        response.setTeamId(teamId);
        response.setName("Core Team");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("actor-1");
        response.setModifiedBy("actor-1");
        return response;
    }
}





