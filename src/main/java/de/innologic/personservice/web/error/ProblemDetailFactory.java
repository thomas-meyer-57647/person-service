package de.innologic.personservice.web.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

public class ProblemDetailFactory {

    public ProblemDetail createProblem(
            HttpStatus status,
            String title,
            String detail,
            String errorCode,
            HttpServletRequest request,
            List<Map<String, Object>> violations
    ) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("message", detail);
        problemDetail.setProperty("timestamp", OffsetDateTime.now(ZoneOffset.UTC).toString());
        problemDetail.setProperty("path", request.getRequestURI());
        if (errorCode != null) {
            problemDetail.setProperty("errorCode", errorCode);
        }
        if (violations != null && !violations.isEmpty()) {
            problemDetail.setProperty("violations", violations);
        }
        return problemDetail;
    }
}
