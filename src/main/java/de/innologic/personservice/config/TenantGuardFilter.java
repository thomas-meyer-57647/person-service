package de.innologic.personservice.config;

import de.innologic.personservice.web.error.SecurityProblemSupport;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TenantGuardFilter extends OncePerRequestFilter {

    private static final Pattern COMPANY_PATH_PATTERN = Pattern.compile("^/api/v1/companies/([^/]+)(?:/.*)?$");

    private final String tenantClaimName;
    private final SecurityProblemSupport securityProblemSupport;

    public TenantGuardFilter(String tenantClaimName, SecurityProblemSupport securityProblemSupport) {
        this.tenantClaimName = tenantClaimName;
        this.securityProblemSupport = securityProblemSupport;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Matcher matcher = COMPANY_PATH_PATTERN.matcher(request.getRequestURI());
        if (!matcher.matches()) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication == null || !authentication.isAuthenticated())
                && request.getUserPrincipal() instanceof Authentication requestAuthentication) {
            authentication = requestAuthentication;
        }
        if ((authentication == null || !authentication.isAuthenticated())
                && request.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)
                instanceof SecurityContext requestSecurityContext) {
            authentication = requestSecurityContext.getAuthentication();
        }
        if (authentication == null || !authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
            return;
        }

        Jwt jwt = resolveJwt(authentication);
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String companyIdFromPath = matcher.group(1);
        String tenantFromToken = normalizeTenant(jwt.getClaim(tenantClaimName));

        if (tenantFromToken == null) {
            securityProblemSupport.writeInvalidTokenProblem(request, response);
            return;
        }
        if (!tenantFromToken.equals(companyIdFromPath)) {
            securityProblemSupport.writeTenantMismatchProblem(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String normalizeTenant(Object claimValue) {
        if (claimValue == null) {
            return null;
        }
        String value = String.valueOf(claimValue).trim();
        return value.isEmpty() ? null : value;
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
}
