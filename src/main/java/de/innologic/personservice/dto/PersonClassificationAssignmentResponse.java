package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "PersonClassificationAssignmentResponse", description = "Details about a classification assignment on a person.")
public class PersonClassificationAssignmentResponse {

    @Schema(description = "Internal ID for troubleshooting.", example = "55")
    private Long id;

    @Schema(description = "Stable public identifier for the assignment.", example = "a7b4c3d9-3f11-44aa-93bb-0d8c59d5e7a1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String assignmentId;

    @Schema(description = "Tenant identifier.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "Public person ID that owns the assignment.", example = "person-uuid-1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String personId;

    @Schema(description = "Public classification ID that is assigned to the person.", example = "classification-uuid-1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String classificationId;

    @Schema(description = "Flag whether this assignment is the person's primary classification.", example = "false")
    private Boolean isPrimary;

    @Schema(description = "Start timestamp (UTC) for the assignment.", example = "2026-01-01T10:00:00")
    private LocalDateTime validFrom;

    @Schema(description = "End timestamp (UTC) for the assignment.", example = "2026-12-31T23:59:59")
    private LocalDateTime validTo;

    @Schema(description = "Creation timestamp (UTC).", example = "2026-01-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Actor that created the assignment.")
    private String createdBy;

    @Schema(description = "Last modification timestamp (UTC).", example = "2026-01-01T11:00:00")
    private LocalDateTime modifiedAt;

    @Schema(description = "Actor that last modified the assignment.")
    private String modifiedBy;

    @Schema(description = "When the assignment was trashed.")
    private LocalDateTime trashedAt;

    @Schema(description = "Actor that trashed the assignment.")
    private String trashedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getTrashedAt() {
        return trashedAt;
    }

    public void setTrashedAt(LocalDateTime trashedAt) {
        this.trashedAt = trashedAt;
    }

    public String getTrashedBy() {
        return trashedBy;
    }

    public void setTrashedBy(String trashedBy) {
        this.trashedBy = trashedBy;
    }
}
