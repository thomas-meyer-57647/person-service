package de.innologic.personservice.web;

import de.innologic.personservice.config.SecurityConfig;
import de.innologic.personservice.web.error.SecurityProblemSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
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
@Import({SecurityConfig.class, SecurityProblemSupport.class})
class SecurityBaselineTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void pingShouldBeAccessibleWithoutJwt() throws Exception {
        mockMvc.perform(get("/person/ping"))
                .andExpect(status().isOk());
    }

    @Test
    void apiV1ShouldReturn401WithoutJwt() throws Exception {
        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 1L))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("AUTHENTICATION_REQUIRED"))
                .andExpect(unauthenticated());
    }
}


