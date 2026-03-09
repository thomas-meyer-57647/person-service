package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Schema(name = "PersonClassificationAssignmentRequest", description = "Payload to bind a classification to a person.")
public class PersonClassificationAssignmentRequest {

    @NotBlank
    @Schema(description = "Public ID of the classification to assign.", example = "c3a2f9c8-7f33-4fb5-8f99-0d8c59d5a7b4", requiredMode = Schema.RequiredMode.REQUIRED)
    private String classificationId;

    @Schema(description = "Set to true when this assignment should be the primary classification for the person.", example = "false")
    private Boolean isPrimary;

    @Schema(description = "Optional start timestamp (UTC) for the assignment.", example = "2026-01-01T10:00:00")
    private LocalDateTime validFrom;

    @Schema(description = "Optional end timestamp (UTC) for the assignment.", example = "2026-12-31T23:59:59")
    private LocalDateTime validTo;

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
}
