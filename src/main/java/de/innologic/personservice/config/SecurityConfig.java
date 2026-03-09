package de.innologic.personservice.config;

import de.innologic.personservice.web.error.ProblemDetailFactory;
import de.innologic.personservice.web.error.SecurityProblemSupport;
import de.innologic.personservice.web.logging.CorrelationIdFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    TenantGuardFilter tenantGuardFilter(
            @Value("${app.security.tenant-claim:tenant_id}") String tenantClaimName,
            SecurityProblemSupport securityProblemSupport
    ) {
        return new TenantGuardFilter(tenantClaimName, securityProblemSupport);
    }

    @Bean
    TenantAuthorizationManager tenantAuthorizationManager(
            @Value("${app.security.tenant-claim:tenant_id}") String tenantClaimName
    ) {
        return new TenantAuthorizationManager(tenantClaimName);
    }

    @Bean
    JwtScopesConverter jwtScopesConverter() {
        return new JwtScopesConverter();
    }

    @Bean
    CorrelationIdFilter correlationIdFilter() {
        return new CorrelationIdFilter();
    }

    @Bean
    @ConditionalOnMissingBean(ProblemDetailFactory.class)
    ProblemDetailFactory problemDetailFactory() {
        return new ProblemDetailFactory();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(JwtScopesConverter jwtScopesConverter) {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtScopesConverter);
        return converter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SecurityProblemSupport securityProblemSupport,
            TenantGuardFilter tenantGuardFilter,
            TenantAuthorizationManager tenantAuthorizationManager,
            JwtAuthenticationConverter jwtAuthenticationConverter,
            CorrelationIdFilter correlationIdFilter
    ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(correlationIdFilter, SecurityContextHolderFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/actuator/health",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/person/ping",
                                "/person/version"
                        ).permitAll()
                        .requestMatchers("/api/v1/companies/**").access(tenantAuthorizationManager)
                        .requestMatchers("/api/v1/**").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(securityProblemSupport)
                        .accessDeniedHandler(securityProblemSupport)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(securityProblemSupport)
                        .accessDeniedHandler(securityProblemSupport)
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                )
                .addFilterAfter(tenantGuardFilter, AuthorizationFilter.class);

        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean(JwtDecoder.class)
    JwtDecoder jwtDecoder(
            @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri,
            @Value("${app.security.required-audience:person-service}") String requiredAudience
    ) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        OAuth2TokenValidator<Jwt> defaultValidator = JwtValidators.createDefault();
        OAuth2TokenValidator<Jwt> audienceValidator = new JwtAudienceValidator(requiredAudience);
        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(defaultValidator, audienceValidator));
        return jwtDecoder;
    }
}
