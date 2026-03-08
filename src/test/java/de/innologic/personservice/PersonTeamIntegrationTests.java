package de.innologic.personservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.innologic.personservice.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
@WithMockUser(authorities = {"SCOPE_person:read", "SCOPE_person:write", "SCOPE_team:read", "SCOPE_team:write"})
class PersonTeamIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper =
            com.fasterxml.jackson.databind.json.JsonMapper.builder()
                    .findAndAddModules()
                    .build();

    @Test
    void createPerson_shouldReturnPublicIdUuidAndCompanyIdString() throws Exception {
        PersonRef created = createPerson("3001", "Alice", "Alice A");

        assertThat(created.internalId()).isPositive();
        assertThat(UUID.fromString(created.publicId())).isNotNull();

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", "3001", created.publicId())
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value("3001"))
                .andExpect(jsonPath("$.personId").value(created.publicId()));
    }

    @Test
    void getPersonByPublicId_shouldReturn200WithSameData() throws Exception {
        PersonRef created = createPerson("3002", "Bob", "Bob B");

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", "3002", created.publicId())
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.internalId()))
                .andExpect(jsonPath("$.personId").value(created.publicId()))
                .andExpect(jsonPath("$.displayName").value("Bob B"));
    }

    @Test
    void updatePersonByPublicId_shouldKeepPersonIdStable() throws Exception {
        PersonRef created = createPerson("3003", "Carol", "Carol Before");

        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", "3003", created.publicId())
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "displayName": "Carol After"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.internalId()))
                .andExpect(jsonPath("$.personId").value(created.publicId()))
                .andExpect(jsonPath("$.displayName").value("Carol After"));
    }

    @Test
    void getPersonWithUnknownPublicId_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", "3004", UUID.randomUUID().toString())
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void getPersonWithWrongCompanyId_shouldNotLeakData() throws Exception {
        PersonRef created = createPerson("3005", "Eve", "Eve Secret");

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", "3999", created.publicId())
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(result -> {
                    int status = result.getResponse().getStatus();
                    assertThat(status == 403 || status == 404).isTrue();
                    String body = result.getResponse().getContentAsString();
                    assertThat(body).doesNotContain("Eve Secret");
                });
    }

    private PersonRef createPerson(String companyId, String givenName, String displayName) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/companies/{companyId}/persons", companyId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": "%s",
                                  "givenName": "%s",
                                  "displayName": "%s"
                                }
                                """.formatted(companyId, givenName, displayName)))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return new PersonRef(json.path("id").asLong(), json.path("personId").asText());
    }

    private record PersonRef(long internalId, String publicId) {
    }
}
