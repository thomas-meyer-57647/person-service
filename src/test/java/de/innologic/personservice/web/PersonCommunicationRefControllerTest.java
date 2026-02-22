package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
import de.innologic.personservice.dto.PersonCommunicationRefsRequest;
import de.innologic.personservice.dto.PersonCommunicationRefsResponse;
import de.innologic.personservice.service.person.PersonCommunicationRefService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonCommunicationRefController.class)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class})
class PersonCommunicationRefControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonCommunicationRefService personCommunicationRefService;

    @Test
    void shouldGetCommunicationRefs_whenPersonExists() throws Exception {
        when(personCommunicationRefService.getRefs("1", "10")).thenReturn(response("1", "10", List.of("c-1", "c-2")));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", 1L, 10L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.personId").value(10))
                .andExpect(jsonPath("$.communicationIds[0]").value("c-1"));
    }

    @Test
    void shouldReturn404_whenGetCommunicationRefsPersonNotFound() throws Exception {
        when(personCommunicationRefService.getRefs("1", "404")).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", 1L, 404L))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Person not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/404/communication-refs"));
    }

    @Test
    void shouldPutCommunicationRefs_whenValidRequest() throws Exception {
        when(personCommunicationRefService.replaceRefs(eq("1"), eq("10"), any(), eq("actor-1")))
                .thenReturn(response("1", "10", List.of("c-1", "c-2")));

        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", 1L, 10L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "communicationIds": ["c-1", "c-2"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.personId").value(10))
                .andExpect(jsonPath("$.communicationIds[1]").value("c-2"));
    }

    @Test
    void shouldReturn400_whenPutCommunicationRefsMissingList() throws Exception {
        mockMvc.perform(put("/api/v1/companies/{companyId}/persons/{personId}/communication-refs", 1L, 10L)
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.message").value(containsString("communicationIds")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/10/communication-refs"));
    }

    private PersonCommunicationRefsResponse response(String companyId, String personId, List<String> ids) {
        PersonCommunicationRefsResponse response = new PersonCommunicationRefsResponse();
        response.setCompanyId(companyId);
        response.setPersonId(personId);
        response.setCommunicationIds(ids);
        return response;
    }
}





