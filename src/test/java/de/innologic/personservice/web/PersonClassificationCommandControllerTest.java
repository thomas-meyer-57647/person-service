package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
import de.innologic.personservice.dto.PersonClassificationAssignmentRequest;
import de.innologic.personservice.dto.PersonClassificationAssignmentResponse;
import de.innologic.personservice.dto.PersonClassificationCreateRequest;
import de.innologic.personservice.dto.PersonClassificationResponse;
import de.innologic.personservice.dto.PersonClassificationUpdateRequest;
import de.innologic.personservice.service.classification.PersonClassificationAssignmentService;
import de.innologic.personservice.service.classification.PersonClassificationCommandService;
import de.innologic.personservice.web.error.ConflictException;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonClassificationCommandController.class)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, TestSecurityConfig.class})
class PersonClassificationCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonClassificationCommandService classificationCommandService;

    @MockitoBean
    private PersonClassificationAssignmentService assignmentService;

    @Test
    void shouldCreateClassification_whenValidRequest() throws Exception {
        when(classificationCommandService.createClassification(eq("company-1"), any(PersonClassificationCreateRequest.class), eq("actor-1")))
                .thenReturn(sampleClassificationResponse());

        mockMvc.perform(post("/api/v1/companies/{companyId}/person-classifications", "company-1")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": "company-1",
                                  "key": "employmentStatus",
                                  "code": "ACTIVE",
                                  "label": "Active employee",
                                  "active": true
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.classificationId").value("classification-1"))
                .andExpect(jsonPath("$.companyId").value("company-1"))
                .andExpect(jsonPath("$.key").value("employmentStatus"))
                .andExpect(jsonPath("$.active").value(true));
    }

    @Test
    void shouldReturn409_whenCreateClassificationConflict() throws Exception {
        when(classificationCommandService.createClassification(eq("company-1"), any(PersonClassificationCreateRequest.class), eq("actor-1")))
                .thenThrow(new ConflictException("duplicate"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/person-classifications", "company-1")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "companyId": "company-1",
                                  "key": "employmentStatus",
                                  "code": "ACTIVE",
                                  "label": "Active employee"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.errorCode").value("CONFLICT"))
                .andExpect(jsonPath("$.path").value("/api/v1/companies/company-1/person-classifications"));
    }

    @Test
    void shouldUpdateClassification_whenValidPatch() throws Exception {
        when(classificationCommandService.updateClassification(eq("company-1"), eq("classification-1"), any(PersonClassificationUpdateRequest.class), eq("actor-1")))
                .thenReturn(sampleClassificationResponse());

        mockMvc.perform(patch("/api/v1/companies/{companyId}/person-classifications/{classificationId}", "company-1", "classification-1")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "label": "Updated label"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label").value("Sample classification"));
    }

    @Test
    void shouldReturn404_whenUpdateClassificationNotFound() throws Exception {
        when(classificationCommandService.updateClassification(eq("company-1"), eq("missing"), any(PersonClassificationUpdateRequest.class), eq("actor-1")))
                .thenThrow(new NotFoundException("not found"));

        mockMvc.perform(patch("/api/v1/companies/{companyId}/person-classifications/{classificationId}", "company-1", "missing")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "label": "Updated label"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldDeactivateClassification_whenFound() throws Exception {
        when(classificationCommandService.deactivateClassification("company-1", "classification-1", "actor-1"))
                .thenReturn(sampleClassificationResponse());

        mockMvc.perform(post("/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate", "company-1", "classification-1")
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.classificationId").value("classification-1"));
    }

    @Test
    void shouldReturn404_whenDeactivateMissing() throws Exception {
        when(classificationCommandService.deactivateClassification("company-1", "missing", "actor-1"))
                .thenThrow(new NotFoundException("not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/person-classifications/{classificationId}/deactivate", "company-1", "missing")
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldAssignClassification_whenValidRequest() throws Exception {
        when(assignmentService.assignClassification(eq("company-1"), eq("person-1"), any(PersonClassificationAssignmentRequest.class), eq("actor-1")))
                .thenReturn(sampleAssignmentResponse());

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/classifications", "company-1", "person-1")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "classificationId": "classification-1",
                                  "isPrimary": true
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.assignmentId").value("assignment-1"))
                .andExpect(jsonPath("$.personId").value("person-1"));
    }

    @Test
    void shouldReturn404_whenAssignClassificationMissingEntity() throws Exception {
        when(assignmentService.assignClassification(eq("company-1"), eq("person-1"), any(PersonClassificationAssignmentRequest.class), eq("actor-1")))
                .thenThrow(new NotFoundException("not found"));

        mockMvc.perform(post("/api/v1/companies/{companyId}/persons/{personId}/classifications", "company-1", "person-1")
                        .header("X-Actor-Id", "actor-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "classificationId": "classification-1"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldRemoveAssignment_whenExists() throws Exception {
        mockMvc.perform(delete("/api/v1/companies/{companyId}/persons/{personId}/classifications/{assignmentId}",
                        "company-1", "person-1", "assignment-1")
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404_whenRemoveMissingAssignment() throws Exception {
        doThrow(new NotFoundException("not found"))
                .when(assignmentService).removeAssignment("company-1", "person-1", "missing", "actor-1");

        mockMvc.perform(delete("/api/v1/companies/{companyId}/persons/{personId}/classifications/{assignmentId}",
                        "company-1", "person-1", "missing")
                        .header("X-Actor-Id", "actor-1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    private PersonClassificationResponse sampleClassificationResponse() {
        PersonClassificationResponse response = new PersonClassificationResponse();
        response.setId(42L);
        response.setClassificationId("classification-1");
        response.setCompanyId("company-1");
        response.setKey("employmentStatus");
        response.setCode("ACTIVE");
        response.setLabel("Sample classification");
        response.setActive(true);
        response.setCreatedAt(LocalDateTime.now().minusDays(1));
        response.setModifiedAt(LocalDateTime.now());
        return response;
    }

    private PersonClassificationAssignmentResponse sampleAssignmentResponse() {
        PersonClassificationAssignmentResponse response = new PersonClassificationAssignmentResponse();
        response.setId(55L);
        response.setAssignmentId("assignment-1");
        response.setCompanyId("company-1");
        response.setPersonId("person-1");
        response.setClassificationId("classification-1");
        response.setIsPrimary(true);
        response.setCreatedAt(LocalDateTime.now().minusMinutes(30));
        response.setModifiedAt(LocalDateTime.now());
        return response;
    }
}
