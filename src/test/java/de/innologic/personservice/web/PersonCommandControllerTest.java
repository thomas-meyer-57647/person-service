package de.innologic.personservice.web;

import de.innologic.personservice.dto.PersonCreateRequest;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.dto.PersonUpdateRequest;
import de.innologic.personservice.service.person.PersonCommandService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonCommandController.class)
@Import(GlobalExceptionHandler.class)
class PersonCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonCommandService personCommandService;

    @Test
    void shouldCreatePerson_whenValidRequest() throws Exception {
        when(personCommandService.createPerson(eq(1L), any(PersonCreateRequest.class), eq("actor-1")))
                .thenReturn(samplePersonResponse(10L, 1L));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", 1L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": 1,
                                  "givenName": "Max",
                                  "displayName": "Max Mustermann"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn400_whenCreatePersonMissingCompanyId() throws Exception {
        mockMvc.perform(post("/api/v1/companies/{companyId}/persons", 1L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "givenName": "Max"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.message").value(containsString("companyId")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons"));
    }

    @Test
    void shouldUpdatePerson_whenValidPatch() throws Exception {
        when(personCommandService.updatePerson(eq(1L), eq(10L), any(PersonUpdateRequest.class), eq("actor-1")))
                .thenReturn(samplePersonResponse(10L, 1L));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", 1L, 10L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "displayName": "Updated Name"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenUpdatePersonNotFound() throws Exception {
        when(personCommandService.updatePerson(eq(1L), eq(404L), any(PersonUpdateRequest.class), eq("actor-1")))
                .thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/persons/{personId}", 1L, 404L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "displayName": "Updated Name"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Person not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/404"));
    }

    @Test
    void shouldTrashPerson_whenPersonExists() throws Exception {
        PersonResponse response = samplePersonResponse(10L, 1L);
        response.setTrashedAt(LocalDateTime.now());
        response.setTrashedBy("actor-1");
        when(personCommandService.trashPerson(1L, 10L, "actor-1")).thenReturn(response);

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", 1L, 10L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.trashedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenTrashPersonNotFound() throws Exception {
        when(personCommandService.trashPerson(1L, 404L, "actor-1")).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/trash", 1L, 404L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Person not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/404/trash"));
    }

    @Test
    void shouldRestorePerson_whenPersonExists() throws Exception {
        PersonResponse response = samplePersonResponse(10L, 1L);
        response.setTrashedAt(null);
        response.setTrashedBy(null);
        when(personCommandService.restorePerson(1L, 10L, "actor-1")).thenReturn(response);

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", 1L, 10L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.trashedAt").isEmpty());
    }

    @Test
    void shouldReturn404_whenRestorePersonNotFound() throws Exception {
        when(personCommandService.restorePerson(1L, 404L, "actor-1")).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/restore", 1L, 404L)
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Person not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/404/restore"));
    }

    private PersonResponse samplePersonResponse(Long id, Long companyId) {
        PersonResponse response = new PersonResponse();
        response.setId(id);
        response.setCompanyId(companyId);
        response.setDisplayName("Max Mustermann");
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedBy("actor-1");
        response.setModifiedBy("actor-1");
        return response;
    }
}

