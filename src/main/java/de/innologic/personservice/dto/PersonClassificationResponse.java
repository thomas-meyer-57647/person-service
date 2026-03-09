package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "PersonClassificationResponse", description = "Describes a tenant-specific classification record.")
public class PersonClassificationResponse {

    @Schema(description = "Internal ID for troubleshooting.", example = "42")
    private Long id;

    @Schema(description = "Stable public identifier for the classification.", example = "c3a2f9c8-7f33-4fb5-8f99-0d8c59d5a7b4", requiredMode = Schema.RequiredMode.REQUIRED)
    private String classificationId;

    @Schema(description = "Tenant identifier.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "Business key for the classification.", example = "employmentStatus")
    private String key;

    @Schema(description = "Code that identifies the value within the key.", example = "ACTIVE")
    private String code;

    @Schema(description = "Display label for UIs or downstream consumers.", example = "Active employee")
    private String label;

    @Schema(description = "Flag if the classification can currently be assigned to people.", example = "true")
    private Boolean active;

    @Schema(description = "Creation timestamp (UTC).", example = "2026-01-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Actor who created the record.")
    private String createdBy;

    @Schema(description = "Last modification timestamp (UTC).", example = "2026-01-01T11:00:00")
    private LocalDateTime modifiedAt;

    @Schema(description = "Actor who last modified the record.")
    private String modifiedBy;

    @Schema(description = "When the record was trashed (null if active).", example = "2026-01-02T12:00:00")
    private LocalDateTime trashedAt;

    @Schema(description = "Actor that trashed the record.")
    private String trashedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
