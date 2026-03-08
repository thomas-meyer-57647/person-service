package de.innologic.personservice.web.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class SecurityProblemSupport implements AuthenticationEntryPoint, AccessDeniedHandler {

    private static final String AUTHENTICATION_REQUIRED = "AUTHENTICATION_REQUIRED";
    private static final String ACCESS_DENIED = "ACCESS_DENIED";
    private static final String INVALID_TOKEN = "INVALID_TOKEN";
    private static final String TENANT_MISMATCH = "TENANT_MISMATCH";

    private final ObjectMapper objectMapper;
    private final ProblemDetailFactory problemDetailFactory;

    public SecurityProblemSupport(ObjectMapper objectMapper, ProblemDetailFactory problemDetailFactory) {
        this.objectMapper = objectMapper;
        this.problemDetailFactory = problemDetailFactory;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException
    ) throws IOException {
        if (authException instanceof InvalidTokenAuthenticationException) {
            writeInvalidTokenProblem(request, response);
            return;
        }
        writeProblem(request, response, HttpStatus.UNAUTHORIZED, "Unauthorized", AUTHENTICATION_REQUIRED);
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        if (accessDeniedException instanceof TenantMismatchAccessDeniedException) {
            writeTenantMismatchProblem(request, response);
            return;
        }
        writeProblem(request, response, HttpStatus.FORBIDDEN, "Forbidden", ACCESS_DENIED);
    }

    public void writeInvalidTokenProblem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeProblem(
                request,
                response,
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                INVALID_TOKEN,
                "Required tenant claim is missing in JWT."
        );
    }

    public void writeTenantMismatchProblem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeProblem(
                request,
                response,
                HttpStatus.FORBIDDEN,
                "Forbidden",
                TENANT_MISMATCH,
                "JWT tenant does not match requested company."
        );
    }

    private void writeProblem(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpStatus status,
            String title,
            String errorCode
    ) throws IOException {
        writeProblem(request, response, status, title, errorCode, status == HttpStatus.UNAUTHORIZED
                ? "Authentication is required to access this resource."
                : "You are not allowed to access this resource.");
    }

    private void writeProblem(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpStatus status,
            String title,
            String errorCode,
            String detail
    ) throws IOException {
        ProblemDetail problem = problemDetailFactory.createProblem(status, title, detail, errorCode, request, null);

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), problem);
    }
}
