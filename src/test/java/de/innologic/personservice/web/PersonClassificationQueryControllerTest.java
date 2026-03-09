package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.service.classification.PersonClassificationQueryService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonClassificationQueryController.class)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class})
class PersonClassificationQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonClassificationQueryService classificationQueryService;

    @Test
    void shouldListClassifications_whenValidRequest() throws Exception {
        when(classificationQueryService.listClassifications("company-1", false))
                .thenReturn(List.of(sampleClassificationResponse()));

        mockMvc.perform(get("/api/v1/companies/{companyId}/person-classifications", "company-1")
                        .param("includeInactive", "false")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].classificationId").value("classification-1"))
                .andExpect(jsonPath("$[0].companyId").value("company-1"));
    }

    @Test
    void shouldReturn400_whenIncludeInactiveNotBoolean() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/person-classifications", "company-1")
                        .param("includeInactive", "maybe")
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_PARAMETER"));
    }

    private PersonClassificationResponse sampleClassificationResponse() {
        PersonClassificationResponse response = new PersonClassificationResponse();
        response.setClassificationId("classification-1");
        response.setCompanyId("company-1");
        response.setKey("employmentStatus");
        response.setCode("ACTIVE");
        response.setLabel("Active employee");
        response.setActive(true);
        response.setModifiedAt(LocalDateTime.now());
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        return response;
    }
}
