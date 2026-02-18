package de.innologic.personservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class JwtScopesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String SCOPE_PREFIX = "SCOPE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();

        Object scpClaim = jwt.getClaim("scp");
        if (scpClaim instanceof Collection<?> scpValues) {
            for (Object value : scpValues) {
                addAuthority(authorities, value);
            }
        }

        String scopeClaim = jwt.getClaimAsString("scope");
        if (scopeClaim != null && !scopeClaim.isBlank()) {
            for (String value : scopeClaim.trim().split("\\s+")) {
                addAuthority(authorities, value);
            }
        }

        return authorities;
    }

    private void addAuthority(Set<GrantedAuthority> authorities, Object scopeValue) {
        if (scopeValue == null) {
            return;
        }
        String value = String.valueOf(scopeValue).trim();
        if (!value.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority(SCOPE_PREFIX + value));
        }
    }
}
