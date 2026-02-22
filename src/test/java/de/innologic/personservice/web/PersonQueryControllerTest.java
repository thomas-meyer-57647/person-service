package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.service.person.PersonQueryService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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

@WebMvcTest(PersonQueryController.class)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class})
class PersonQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonQueryService personQueryService;

    @Test
    void shouldListPersons_whenValidRequest() throws Exception {
        PersonResponse response = samplePersonResponse(10L, "1");
        when(personQueryService.listPersons(eq("1"), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(response), PageRequest.of(0, 20), 1));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 1L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.content[0].id").value(10))
                .andExpect(jsonPath("$.content[0].companyId").value(1))
                .andExpect(jsonPath("$.content[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.content[0].modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn400_whenInvalidIncludeTrashedParameter() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 1L)
                        .param("includeTrashed", "not-boolean"))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("INVALID_PARAMETER"))
                .andExpect(jsonPath("$.message").value(containsString("Invalid request parameter")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons"));
    }

    @Test
    void shouldGetPerson_whenPersonExists() throws Exception {
        when(personQueryService.getPerson("1", "10")).thenReturn(samplePersonResponse(10L, "1"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", 1L, 10L))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.companyId").value(1))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.modifiedAt").isNotEmpty());
    }

    @Test
    void shouldReturn404_whenPersonDoesNotExist() throws Exception {
        when(personQueryService.getPerson("1", "99")).thenThrow(new NotFoundException("Person not found"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons/{personId}", 1L, 99L))
                .andExpect(status().isNotFound())
                .andExpect(header().string("Content-Type", containsString("json")))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value(containsString("Person not found")))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/1/persons/99"));
    }

    private PersonResponse samplePersonResponse(Long id, String companyId) {
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





