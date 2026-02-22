package de.innologic.personservice.web;

import de.innologic.personservice.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = OpsController.class,
        properties = {
                "spring.application.name=person-service",
                "application.version=1.0.0"
        }
)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class OpsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPingWithFallbackVersion_whenBuildPropertiesMissingAndBasePathUnset() throws Exception {
        mockMvc.perform(get("/person/ping"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.service").value("person-service"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    void shouldReturnVersionWithFallbackVersion_whenBuildPropertiesMissing() throws Exception {
        mockMvc.perform(get("/person/version"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.service").value("person-service"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }
}

@WebMvcTest(
        value = OpsController.class,
        properties = {
                "spring.application.name=person-service",
                "application.version=1.0.0"
        }
)
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class OpsControllerBuildPropertiesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BuildProperties buildProperties;

    @Test
    void shouldReturnVersionWithBuildProperties_whenAvailable() throws Exception {
        when(buildProperties.getVersion()).thenReturn("2.3.4");
        when(buildProperties.getTime()).thenReturn(Instant.parse("2026-02-13T12:00:00Z"));

        mockMvc.perform(get("/person/version"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.service").value("person-service"))
                .andExpect(jsonPath("$.version").value("2.3.4"))
                .andExpect(jsonPath("$.buildTime").value(containsString("2026-02-13T12:00:00Z")));
    }
}


