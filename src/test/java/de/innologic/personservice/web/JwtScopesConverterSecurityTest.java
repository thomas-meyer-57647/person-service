package de.innologic.personservice.web;

import de.innologic.personservice.config.JwtScopesConverter;
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

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScopeProbeController.class)
@ActiveProfiles("test")
@Import({SecurityConfig.class, SecurityProblemSupport.class})
class JwtScopesConverterSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    private final JwtScopesConverter jwtScopesConverter = new JwtScopesConverter();

    @Test
    void shouldAllowWhenScpContainsRequiredScope() throws Exception {
        mockMvc.perform(get("/api/v1/scope-probe/read")
                        .with(jwt().jwt(jwt -> jwt.claim("scp", List.of("person:read")))
                                .authorities(jwtScopesConverter)))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    void shouldReturn403WhenScopeDoesNotContainRequiredPermission() throws Exception {
        mockMvc.perform(get("/api/v1/scope-probe/read")
                        .with(jwt().jwt(jwt -> jwt.claim("scp", List.of("person:write")))
                                .authorities(jwtScopesConverter)))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }
}
