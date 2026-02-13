package de.innologic.personservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonTeamIntegrationTests extends AbstractMariaDbIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldListPersons_whenDataExists() throws Exception {
        long companyId = 2001L;
        long personId = createPerson(companyId, "Alice", "Alice A");
        assertThat(personId).isPositive();

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", companyId)
                        .param("includeTrashed", "false")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].id").value(personId))
                .andExpect(jsonPath("$.content[0].companyId").value(companyId));
    }

    @Test
    void shouldReturn400_whenListPersonsHasInvalidIncludeTrashedParam() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 2002L)
                        .param("includeTrashed", "invalid")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldGetPersonById_whenPersonExists() throws Exception {
        long companyId = 2003L;
        long personId = createPerson(companyId, "Bob", "Bob B");

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", companyId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(personId))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenGetPersonByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", 2004L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldCreatePerson_whenValidRequest() throws Exception {
        long companyId = 2005L;

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", companyId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 2005,
                                  "givenName": "Create",
                                  "displayName": "Create Person"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.createdBy").value("it-tester"));
    }

    @Test
    void shouldReturn400_whenCreatePersonMissingCompanyId() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", 2006L)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "givenName": "Invalid"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldPatchPerson_whenPersonExists() throws Exception {
        long companyId = 2007L;
        long personId = createPerson(companyId, "Patch", "Patch Before");

        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", companyId, personId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "displayName": "Patch After"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(personId))
                .andExpect(jsonPath("$.displayName").value("Patch After"));
    }

    @Test
    void shouldReturn404_whenPatchPersonNotFound() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", 2008L, 99999L)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "displayName": "Nope"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldTrashPerson_andFilterByIncludeTrashed() throws Exception {
        long companyId = 2009L;
        long personId = createPerson(companyId, "Trash", "Trash Me");

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", companyId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.trashedAt").isNotEmpty())
                .andExpect(jsonPath("$.trashedBy").value("it-tester"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", companyId)
                        .param("includeTrashed", "false")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(0));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", companyId)
                        .param("includeTrashed", "true")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].id").value(personId));
    }

    @Test
    void shouldReturn404_whenTrashPersonNotFound() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", 2010L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldRestorePerson_whenPersonIsTrashed() throws Exception {
        long companyId = 2011L;
        long personId = createPerson(companyId, "Restore", "Restore Me");
        trashPerson(companyId, personId);

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", companyId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(personId))
                .andExpect(jsonPath("$.trashedAt").isEmpty());
    }

    @Test
    void shouldReturn404_whenRestorePersonNotFound() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", 2012L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldListTeams_whenDataExists() throws Exception {
        long companyId = 2013L;
        long teamId = createTeam(companyId, "Team List");
        assertThat(teamId).isPositive();

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", companyId)
                        .param("includeTrashed", "false")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].id").value(teamId))
                .andExpect(jsonPath("$.content[0].companyId").value(companyId));
    }

    @Test
    void shouldReturn400_whenListTeamsHasInvalidIncludeTrashedParam() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams", 2014L)
                        .param("includeTrashed", "x")
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldGetTeamById_whenTeamExists() throws Exception {
        long companyId = 2015L;
        long teamId = createTeam(companyId, "Team By Id");

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", companyId, teamId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(teamId))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenGetTeamByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}", 2016L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldListTeamMembers_whenMembersExist() throws Exception {
        long companyId = 2017L;
        long personId = createPerson(companyId, "Member", "Member One");
        long teamId = createTeam(companyId, "Team Members");
        addTeamMember(companyId, teamId, personId);

        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", companyId, teamId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$[0].companyId").value(companyId))
                .andExpect(jsonPath("$[0].teamId").value(teamId))
                .andExpect(jsonPath("$[0].personId").value(personId));
    }

    @Test
    void shouldReturn404_whenListTeamMembersTeamNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/teams/{teamId}/members", 2018L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldCreateTeam_whenValidRequest() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", 2019L)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 2019,
                                  "name": "Create Team",
                                  "description": "desc"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyId").value(2019))
                .andExpect(jsonPath("$.name").value("Create Team"));
    }

    @Test
    void shouldReturn409_whenCreateTeamDuplicateNameInCompany() throws Exception {
        long companyId = 2020L;
        createTeam(companyId, "Unique Clash");

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams", companyId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 2020,
                                  "name": "Unique Clash"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldPatchTeam_whenTeamExists() throws Exception {
        long companyId = 2021L;
        long teamId = createTeam(companyId, "Patch Team");

        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", companyId, teamId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "patched"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(teamId))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.description").value("patched"));
    }

    @Test
    void shouldReturn404_whenPatchTeamNotFound() throws Exception {
        mockMvc.perform(patch("/api/v1/companies/{companyId}/teams/{teamId}", 2022L, 99999L)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "description": "patched"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldTrashTeam_whenTeamExists() throws Exception {
        long companyId = 2023L;
        long teamId = createTeam(companyId, "Trash Team");

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", companyId, teamId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(teamId))
                .andExpect(jsonPath("$.trashedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenTrashTeamNotFound() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", 2024L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldRestoreTeam_whenTeamWasTrashed() throws Exception {
        long companyId = 2025L;
        long teamId = createTeam(companyId, "Restore Team");
        trashTeam(companyId, teamId);

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", companyId, teamId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(teamId))
                .andExpect(jsonPath("$.trashedAt").isEmpty());
    }

    @Test
    void shouldReturn404_whenRestoreTeamNotFound() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/restore", 2026L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldAddTeamMember_whenValidRequest() throws Exception {
        long companyId = 2027L;
        long personId = createPerson(companyId, "Join", "Join Person");
        long teamId = createTeam(companyId, "Join Team");

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", companyId, teamId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "personId": %d,
                                  "role": "Developer"
                                }
                                """.formatted(personId)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.teamId").value(teamId))
                .andExpect(jsonPath("$.personId").value(personId));
    }

    @Test
    void shouldReturn409_whenAddTeamMemberDuplicate() throws Exception {
        long companyId = 2028L;
        long personId = createPerson(companyId, "Dup", "Dup Person");
        long teamId = createTeam(companyId, "Dup Team");
        addTeamMember(companyId, teamId, personId);

        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", companyId, teamId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "personId": %d,
                                  "role": "Developer"
                                }
                                """.formatted(personId)))
                .andExpect(status().isConflict())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldRemoveTeamMember_whenMembershipExists() throws Exception {
        long companyId = 2029L;
        long personId = createPerson(companyId, "Leave", "Leave Person");
        long teamId = createTeam(companyId, "Leave Team");
        addTeamMember(companyId, teamId, personId);

        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", companyId, teamId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404_whenRemoveTeamMemberNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/teams/{teamId}/members/{personId}", 2030L, 99999L, 12345L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldGetCommunicationRefs_whenPersonExists() throws Exception {
        long companyId = 2031L;
        long personId = createPerson(companyId, "Comm", "Comm Person");
        putCommunicationRefs(companyId, personId, """
                {
                  "communicationIds": ["comm-1", "comm-2"]
                }
                """);

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", companyId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.personId").value(personId))
                .andExpect(jsonPath("$.communicationIds[0]").value("comm-1"));
    }

    @Test
    void shouldReturn404_whenGetCommunicationRefsPersonNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", 2032L, 99999L)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    @Test
    void shouldPutCommunicationRefs_whenValidRequest() throws Exception {
        long companyId = 2033L;
        long personId = createPerson(companyId, "CommPut", "CommPut Person");

        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", companyId, personId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "communicationIds": ["c-1", "c-2"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value(companyId))
                .andExpect(jsonPath("$.personId").value(personId))
                .andExpect(jsonPath("$.communicationIds.length()").value(2));
    }

    @Test
    void shouldReturn400_whenPutCommunicationRefsValidationFails() throws Exception {
        long companyId = 2034L;
        long personId = createPerson(companyId, "CommBad", "CommBad Person");

        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", companyId, personId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")));
    }

    private long createPerson(long companyId, String givenName, String displayName) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/companies/{companyId}/persons", companyId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": %d,
                                  "givenName": "%s",
                                  "displayName": "%s"
                                }
                                """.formatted(companyId, givenName, displayName)))
                .andExpect(status().isCreated())
                .andReturn();
        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.path("id").asLong();
    }

    private long createTeam(long companyId, String name) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/companies/{companyId}/teams", companyId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": %d,
                                  "name": "%s"
                                }
                                """.formatted(companyId, name)))
                .andExpect(status().isCreated())
                .andReturn();
        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.path("id").asLong();
    }

    private void addTeamMember(long companyId, long teamId, long personId) throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/members", companyId, teamId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "personId": %d,
                                  "role": "Developer"
                                }
                                """.formatted(personId)))
                .andExpect(status().isCreated());
    }

    private void trashPerson(long companyId, long personId) throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", companyId, personId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk());
    }

    private void trashTeam(long companyId, long teamId) throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/teams/{teamId}/trash", companyId, teamId)
                        .header("X-Actor-Id", "it-tester"))
                .andExpect(status().isOk());
    }

    private void putCommunicationRefs(long companyId, long personId, String body) throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", companyId, personId)
                        .header("X-Actor-Id", "it-tester")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }
}
