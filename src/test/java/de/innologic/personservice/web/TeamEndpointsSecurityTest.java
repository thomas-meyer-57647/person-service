package de.innologic.personservice.web;

import de.innologic.personservice.config.JwtScopesConverter;
import de.innologic.personservice.config.SecurityConfig;
import de.innologic.personservice.dto.TeamCreateRequest;
import de.innologic.personservice.dto.TeamMemberAddRequest;
import de.innologic.personservice.dto.TeamMemberResponse;
import de.innologic.personservice.dto.TeamResponse;
import de.innologic.personservice.dto.TeamUpdateRequest;
import de.innologic.personservice.service.team.TeamCommandService;
import de.innologic.personservice.service.team.TeamQueryService;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import de.innologic.personservice.web.error.SecurityProblemSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({TeamCommandController.class, TeamQueryController.class})
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, SecurityConfig.class, SecurityProblemSupport.class})
class TeamEndpointsSecurityTest {

    private static final String COMPANY_ID = "100";
    private static final String OTHER_COMPANY_ID = "200";
    private static final String TEAM_ID = "team-20";
    private static final String PERSON_ID = "person-10";
    private static final String MEMBERSHIP_ID = "membership-99";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeamCommandService teamCommandService;

    @MockitoBean
    private TeamQueryService teamQueryService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    private final JwtScopesConverter jwtScopesConverter = new JwtScopesConverter();

    @Test
    void createTeam_shouldReturn201_withJwtTenantAndWriteScope() throws Exception {
        when(teamCommandService.createTeam(eq(COMPANY_ID), any(TeamCreateRequest.class), eq(null)))
                .thenReturn(sampleTeamResponse(TEAM_ID, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateTeamBody(COMPANY_ID))
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isCreated());
    }

    @Test
    void createTeam_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateTeamBody(COMPANY_ID)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createTeam_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateTeamBody(COMPANY_ID))
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createTeam_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", OTHER_COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateTeamBody(OTHER_COMPANY_ID))
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void updateTeam_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(teamCommandService.updateTeam(eq(COMPANY_ID), eq(TEAM_ID), any(TeamUpdateRequest.class), eq(null)))
                .thenReturn(sampleTeamResponse(TEAM_ID, COMPANY_ID));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateTeamBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isOk());
    }

    @Test
    void updateTeam_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateTeamBody()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateTeam_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateTeamBody())
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updateTeam_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", OTHER_COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateTeamBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void trashTeam_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(teamCommandService.trashTeam(COMPANY_ID, TEAM_ID, null))
                .thenReturn(sampleTeamResponse(TEAM_ID, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isOk());
    }

    @Test
    void trashTeam_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", COMPANY_ID, TEAM_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void trashTeam_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void trashTeam_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", OTHER_COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void restoreTeam_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(teamCommandService.restoreTeam(COMPANY_ID, TEAM_ID, null))
                .thenReturn(sampleTeamResponse(TEAM_ID, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isOk());
    }

    @Test
    void restoreTeam_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", COMPANY_ID, TEAM_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restoreTeam_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void restoreTeam_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", OTHER_COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void addMember_shouldReturn201_withJwtTenantAndWriteScope() throws Exception {
        when(teamCommandService.addTeamMember(eq(COMPANY_ID), eq(TEAM_ID), any(TeamMemberAddRequest.class), eq(null)))
                .thenReturn(sampleMemberResponse(COMPANY_ID, TEAM_ID, PERSON_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validAddMemberBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isCreated());
    }

    @Test
    void addMember_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validAddMemberBody()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void addMember_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validAddMemberBody())
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void addMember_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", OTHER_COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validAddMemberBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void addMember_shouldReturn409_whenMembershipAlreadyActive() throws Exception {
        when(teamCommandService.addTeamMember(eq(COMPANY_ID), eq(TEAM_ID), any(TeamMemberAddRequest.class), eq(null)))
                .thenThrow(new ConflictException("Person is already an active member"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validAddMemberBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isConflict());
    }

    @Test
    void removeMember_shouldReturn204_withJwtTenantAndWriteScope() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", COMPANY_ID, TEAM_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeMember_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", COMPANY_ID, TEAM_ID, PERSON_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void removeMember_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", COMPANY_ID, TEAM_ID, PERSON_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void removeMember_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", OTHER_COMPANY_ID, TEAM_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void listTeams_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(teamQueryService.listTeams(eq(COMPANY_ID), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(sampleTeamResponse(TEAM_ID, COMPANY_ID)), PageRequest.of(0, 20), 1));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", COMPANY_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isOk());
    }

    @Test
    void listTeams_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", COMPANY_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void listTeams_shouldReturn403_withoutReadScope() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", COMPANY_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void listTeams_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", OTHER_COMPANY_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void getTeam_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(teamQueryService.getTeam(COMPANY_ID, TEAM_ID)).thenReturn(sampleTeamResponse(TEAM_ID, COMPANY_ID));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isOk());
    }

    @Test
    void getTeam_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getTeam_shouldReturn403_withoutReadScope() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getTeam_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", OTHER_COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void getTeam_shouldReturn404_whenTeamNotFound() throws Exception {
        String missingTeamId = "team-999";
        when(teamQueryService.getTeam(COMPANY_ID, missingTeamId)).thenThrow(new NotFoundException("Team not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", COMPANY_ID, missingTeamId)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTeamMembers_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(teamQueryService.getTeamMembers(COMPANY_ID, TEAM_ID))
                .thenReturn(List.of(sampleMemberResponse(COMPANY_ID, TEAM_ID, PERSON_ID)));

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isOk());
    }

    @Test
    void getTeamMembers_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getTeamMembers_shouldReturn403_withoutReadScope() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getTeamMembers_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", OTHER_COMPANY_ID, TEAM_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "team:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    private RequestPostProcessor jwtWithTenantAndScope(String tenant, String scope) {
        return jwt().jwt(j -> j.claim("tenant_id", tenant).claim("scp", List.of(scope)))
                .authorities(jwtScopesConverter);
    }

    private RequestPostProcessor jwtWithTenantNoScope(String tenant) {
        return jwt().jwt(j -> j.claim("tenant_id", tenant));
    }

    private String validCreateTeamBody(String companyId) {
        return """
                {
                  "companyId": "%s",
                  "name": "Core Team",
                  "description": "Main team"
                }
                """.formatted(companyId);
    }

    private String validUpdateTeamBody() {
        return """
                {
                  "description": "Updated description"
                }
                """;
    }

    private String validAddMemberBody() {
        return """
                {
                  "personId": "%s",
                  "role": "Developer"
                }
                """.formatted(PERSON_ID);
    }

    private TeamResponse sampleTeamResponse(String teamId, String companyId) {
        TeamResponse response = new TeamResponse();
        response.setCompanyId(companyId);
        response.setTeamId(teamId);
        response.setName("Core Team");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        return response;
    }

    private TeamMemberResponse sampleMemberResponse(String companyId, String teamId, String personId) {
        TeamMemberResponse response = new TeamMemberResponse();
        response.setCompanyId(companyId);
        response.setTeamId(teamId);
        response.setPersonId(personId);
        response.setMembershipId(MEMBERSHIP_ID);
        response.setIsPrimary(Boolean.TRUE);
        response.setRole("Developer");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        return response;
    }
}




