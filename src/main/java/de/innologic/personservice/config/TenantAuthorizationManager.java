package de.innologic.personservice.config;

import de.innologic.personservice.web.error.InvalidTokenAuthenticationException;
import de.innologic.personservice.web.error.TenantMismatchAccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Supplier;

public class TenantAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Pattern COMPANY_PATH_PATTERN = Pattern.compile("^/api/v1/companies/([^/]+)(?:/.*)?$");

    private final String tenantClaimName;

    public TenantAuthorizationManager(String tenantClaimName) {
        this.tenantClaimName = tenantClaimName;
    }

    @Override
    public AuthorizationResult authorize(
            Supplier<? extends Authentication> authenticationSupplier,
            RequestAuthorizationContext object
    ) {
        Matcher matcher = COMPANY_PATH_PATTERN.matcher(object.getRequest().getRequestURI());
        if (!matcher.matches()) {
            return new AuthorizationDecision(true);
        }

        Authentication authentication = authenticationSupplier.get();
        if (authentication == null || !authentication.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }

        Jwt jwt = resolveJwt(authentication);
        if (jwt == null) {
            throw new InvalidTokenAuthenticationException("JWT token is missing in authentication.");
        }

        String tenant = normalize(jwt.getClaim(tenantClaimName));
        if (tenant == null) {
            throw new InvalidTokenAuthenticationException("Tenant claim is missing in JWT.");
        }

        String companyId = matcher.group(1);
        if (!tenant.equals(companyId)) {
            throw new TenantMismatchAccessDeniedException("Tenant does not match companyId.");
        }

        return new AuthorizationDecision(true);
    }

    private Jwt resolveJwt(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            return jwtAuthenticationToken.getToken();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Jwt jwt) {
            return jwt;
        }
        return null;
    }

    private String normalize(Object claim) {
        if (claim == null) {
            return null;
        }
        String value = String.valueOf(claim).trim();
        return value.isEmpty() ? null : value;
    }
}
