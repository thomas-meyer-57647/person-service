package de.innologic.personservice.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "ProblemDetailResponse", description = "Standardized error response emitted by the API.")
public record ProblemDetailResponse(
        @Schema(description = "HTTP status code returned by the endpoint", example = "400") Integer status,
        @Schema(description = "Short title describing the error", example = "Bad Request") String title,
        @Schema(description = "Detailed explanation of the situation", example = "Validation failed for field displayName") String detail,
        @Schema(description = "Request path that triggered the error", example = "/api/v1/companies/company-123/persons") String path,
        @Schema(description = "Timestamp when the error was created (UTC)", example = "2026-03-08T12:00:00Z") String timestamp,
        @Schema(description = "Optional business error code aligned with the Pflichtenheft", example = "VALIDATION_ERROR") String errorCode,
        @Schema(description = "List of field-level violations providing fix guidance") List<Violation> violations
) {
    @Schema(name = "Violation", description = "Field-specific validation failure")
    public record Violation(
            @Schema(description = "Field or parameter that failed validation", example = "displayName") String field,
            @Schema(description = "Human readable validation message", example = "must not be blank") String message,
            @Schema(description = "Rejected value when available", example = "" ) Object rejectedValue
    ) {
    }
}
