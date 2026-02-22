package de.innologic.personservice.web;

import de.innologic.personservice.config.SecurityConfig;
import de.innologic.personservice.dto.PersonResponse;
import de.innologic.personservice.service.person.PersonQueryService;
import de.innologic.personservice.web.error.GlobalExceptionHandler;
import de.innologic.personservice.web.error.SecurityProblemSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonQueryController.class)
@ActiveProfiles("test")
@Import({GlobalExceptionHandler.class, SecurityConfig.class, SecurityProblemSupport.class})
class TenantGuardFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @MockitoBean
    private PersonQueryService personQueryService;

    @MockitoBean
    private JwtDecoder jwtDecoder;

    @Test
    void tenantGuardFilterShouldBeInSecurityFilterChain() {
        boolean present = filterChainProxy.getFilters("/api/v1/companies/100/persons").stream()
                .anyMatch(filter -> filter.getClass().getName().contains("TenantGuardFilter"));

        assertThat(present).isTrue();
    }

    @Test
    void shouldReturn403WhenTenantDoesNotMatchCompanyId() throws Exception {
        when(jwtDecoder.decode("mismatch-token")).thenReturn(jwtWithTenant("100", "mismatch-token"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 200L)
                        .header("Authorization", "Bearer mismatch-token")
                        .with(jwt().jwt(jwt -> jwt.claim("tenant_id", "100"))))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errorCode").value("TENANT_MISMATCH"));
    }

    @Test
    void shouldAllowRequestWhenTenantMatchesCompanyId() throws Exception {
        when(personQueryService.listPersons(eq("100"), isNull(), eq(false), eq(PageRequest.of(0, 20))))
                .thenReturn(new PageImpl<>(List.of(new PersonResponse()), PageRequest.of(0, 20), 1));
        when(jwtDecoder.decode("match-token")).thenReturn(jwtWithTenant("100", "match-token"));

        mockMvc.perform(get("/api/v1/companies/{companyId}/persons", 100L)
                        .header("Authorization", "Bearer match-token")
                        .with(jwt().jwt(jwt -> jwt
                                .claim("tenant_id", "100")
                                .claim("scope", "person:read"))))
                .andExpect(status().isOk())
                .andExpect(authenticated().withAuthentication(authentication ->
                        assertThat(authentication).isInstanceOf(JwtAuthenticationToken.class)));

        verify(personQueryService).listPersons(eq("100"), isNull(), eq(false), eq(PageRequest.of(0, 20)));
    }

    private Jwt jwtWithTenant(String tenantId, String tokenValue) {
        return Jwt.withTokenValue(tokenValue)
                .header("alg", "none")
                .subject("test-user")
                .claim("scope", "read")
                .claim("tenant_id", tenantId)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(300))
                .build();
    }
}


