package de.innologic.personservice.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class CurrentActor {

    public String subjectOrSystem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return "system";
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "system";
        }

        String subject = null;
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            subject = jwtAuthenticationToken.getToken().getSubject();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            subject = jwt.getSubject();
        }

        if (subject == null || subject.isBlank()) {
            return "system";
        }
        return subject.trim();
    }
}
