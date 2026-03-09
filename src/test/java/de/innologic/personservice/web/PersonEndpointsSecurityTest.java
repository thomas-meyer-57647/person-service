package de.innologic.personservice.web;

import de.innologic.personservice.config.JwtScopesConverter;
import de.innologic.personservice.config.SecurityConfig;
import de.innologic.personservice.dto.PersonCommunicationRefsResponse;
import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.service.person.PersonCommandService;
import de.innologic.personservice.service.person.PersonCommunicationRefService;
import de.innologic.personservice.service.person.PersonQueryService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.InvalidTokenAuthenticationException;
import de.innologic.personservice.web.error.NotFoundException;
import de.innologic.personservice.web.error.SecurityProblemSupport;
import de.innologic.personservice.web.logging.CorrelationIdFilter;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({PersonQueryController.class, PersonCommandController.class, PersonCommunicationRefController.class})
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, SecurityConfig.class, SecurityProblemSupport.class})
class PersonEndpointsSecurityTest {

    private static final String COMPANY_ID = "100";
    private static final String OTHER_COMPANY_ID = "200";
    private static final String PERSON_ID = "10";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonQueryService personQueryService;

    @MockitoBean
    private PersonCommandService personCommandService;

    @MockitoBean
    private PersonCommunicationRefService personCommunicationRefService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    private final JwtScopesConverter jwtScopesConverter = new JwtScopesConverter();

    @Test
    void getPersons_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(personQueryService.listPersons(eq(COMPANY_ID), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(samplePersonResponse(10L, COMPANY_ID)), PageRequest.of(0, 20), 1));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isOk());
    }

    @Test
    void getPersons_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", COMPANY_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getPersons_shouldReturn403_withoutReadScope() throws Exception {
        String personsPath = "/api/v1/companies/" + COMPANY_ID + "/persons";
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.errorCode").value("ACCESS_DENIED"))
                .andExpect(jsonPath("$.path").value(personsPath));
    }

    @Test
    void getPersons_shouldReturn401_withInvalidToken() throws Exception {
        String personsPath = "/api/v1/companies/" + COMPANY_ID + "/persons";
        when(jwtDecoder.decode("bad-token")).thenThrow(new InvalidTokenAuthenticationException("token invalid"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .header("Authorization", "Bearer bad-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("INVALID_TOKEN"))
                .andExpect(jsonPath("$.path").value(personsPath))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void getPersons_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", OTHER_COMPANY_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void createPerson_shouldReturn201_withJwtTenantAndWriteScope() throws Exception {
        when(personCommandService.createPerson(eq(COMPANY_ID), any(PersonCreateRequest.class), eq(null)))
                .thenReturn(samplePersonResponse(2L, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateBody(COMPANY_ID))
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isCreated());
    }

    @Test
    void createPerson_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateBody(COMPANY_ID)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("AUTHENTICATION_REQUIRED"));
    }

    @Test
    void createPerson_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateBody(COMPANY_ID))
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createPerson_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", OTHER_COMPANY_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCreateBody(OTHER_COMPANY_ID))
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void updatePerson_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(personCommandService.updatePerson(eq(COMPANY_ID), eq(PERSON_ID), any(PersonUpdateRequest.class), eq(null)))
                .thenReturn(samplePersonResponse(10L, COMPANY_ID));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateBody()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updatePerson_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateBody())
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void updatePerson_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", OTHER_COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void trashPerson_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(personCommandService.trashPerson(COMPANY_ID, PERSON_ID, null))
                .thenReturn(samplePersonResponse(10L, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isOk());
    }

    @Test
    void trashPerson_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", COMPANY_ID, PERSON_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void trashPerson_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void trashPerson_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", OTHER_COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void restorePerson_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(personCommandService.restorePerson(COMPANY_ID, PERSON_ID, null))
                .thenReturn(samplePersonResponse(10L, COMPANY_ID));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isOk());
    }

    @Test
    void restorePerson_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", COMPANY_ID, PERSON_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void restorePerson_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void restorePerson_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", OTHER_COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void getPerson_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(personQueryService.getPerson(COMPANY_ID, PERSON_ID)).thenReturn(samplePersonResponse(10L, COMPANY_ID));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isOk());
    }

    @Test
    void getPerson_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getPerson_shouldReturn403_withoutReadScope() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getPerson_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", OTHER_COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void getPerson_shouldReturn404_whenPersonNotFound() throws Exception {
        when(personQueryService.getPerson(COMPANY_ID, "999")).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, 999L)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPerson_shouldIncludeCorrelationIdHeader_whenMissing() throws Exception {
        when(personQueryService.getPerson(COMPANY_ID, PERSON_ID)).thenReturn(samplePersonResponse(10L, COMPANY_ID));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isOk())
                .andExpect(header().exists(CorrelationIdFilter.HEADER));
    }

    @Test
    void getPerson_shouldReturnProvidedCorrelationIdHeader() throws Exception {
        when(personQueryService.getPerson(COMPANY_ID, PERSON_ID)).thenReturn(samplePersonResponse(10L, COMPANY_ID));
        String incomingCorrelationId = "incoming-correlation-999";

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", COMPANY_ID, PERSON_ID)
                        .header(CorrelationIdFilter.HEADER, incomingCorrelationId)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isOk())
                .andExpect(header().string(CorrelationIdFilter.HEADER, incomingCorrelationId));
    }

    @Test
    void getCommunicationRefs_shouldReturn200_withJwtTenantAndReadScope() throws Exception {
        when(personCommunicationRefService.getRefs(COMPANY_ID, PERSON_ID))
                .thenReturn(sampleCommunicationRefsResponse(COMPANY_ID, PERSON_ID));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isOk());
    }

    @Test
    void getCommunicationRefs_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getCommunicationRefs_shouldReturn403_withoutReadScope() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getCommunicationRefs_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", OTHER_COMPANY_ID, PERSON_ID)
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:read")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void replaceCommunicationRefs_shouldReturn200_withJwtTenantAndWriteScope() throws Exception {
        when(personCommunicationRefService.replaceRefs(eq(COMPANY_ID), eq(PERSON_ID), eq(List.of("c-1", "c-2")), eq(null)))
                .thenReturn(sampleCommunicationRefsResponse(COMPANY_ID, PERSON_ID));

        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCommunicationRefsBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isOk());
    }

    @Test
    void replaceCommunicationRefs_shouldReturn401_withoutJwt() throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCommunicationRefsBody()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void replaceCommunicationRefs_shouldReturn403_withoutWriteScope() throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCommunicationRefsBody())
                        .with(jwtWithTenantNoScope(COMPANY_ID)))
                .andExpect(status().isForbidden());
    }

    @Test
    void replaceCommunicationRefs_shouldReturn403TenantMismatch_withJwt() throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", OTHER_COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCommunicationRefsBody())
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void replaceCommunicationRefs_shouldReturn400_whenCommunicationIdsMissing() throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", COMPANY_ID, PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .with(jwtWithTenantAndScope(COMPANY_ID, "person:write")))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.violations").isArray())
                .andExpect(jsonPath("$.violations[0].field").value("communicationIds"));
    }

    private RequestPostProcessor jwtWithTenantAndScope(String tenant, String scope) {
        return jwt().jwt(j -> j.claim("tenant_id", tenant).claim("scp", List.of(scope)))
                .authorities(jwtScopesConverter);
    }

    private RequestPostProcessor jwtWithTenantNoScope(String tenant) {
        return jwt().jwt(j -> j.claim("tenant_id", tenant));
    }

    private String validCreateBody(String companyId) {
        return """
                {
                  "companyId": "%s",
                  "givenName": "Max",
                  "displayName": "Max Mustermann"
                }
                """.formatted(companyId);
    }

    private String validUpdateBody() {
        return """
                {
                  "displayName": "Updated Name"
                }
                """;
    }

    private String validCommunicationRefsBody() {
        return """
                {
                  "communicationIds": ["c-1", "c-2"]
                }
                """;
    }

    private PersonResponse samplePersonResponse(Long id, String companyId) {
        PersonResponse response = new PersonResponse();
        response.setId(id);
        response.setCompanyId(companyId);
        response.setDisplayName("Sample");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        return response;
    }

    private PersonCommunicationRefsResponse sampleCommunicationRefsResponse(String companyId, String personId) {
        PersonCommunicationRefsResponse response = new PersonCommunicationRefsResponse();
        response.setCompanyId(companyId);
        response.setPersonId(personId);
        response.setCommunicationIds(List.of("c-1", "c-2"));
        return response;
    }
}





