package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TeamResponse", description = "Tenant-aware team metadata returned by the person-service.")
public class TeamResponse {

    @Schema(description = "Public team ID used in REST paths and downstream integrations.", example = "team-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String teamId;

    @Schema(description = "Tenant (company) identifier owning the team.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "Human-friendly team name.", example = "Platform Engineering", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Optional description of the team's responsibilities.", example = "Builds shared platform capabilities.")
    private String description;

    @Schema(description = "Record creation timestamp (UTC).", example = "2026-01-01T10:00:00Z")
    private LocalDateTime createdAt;

    @Schema(description = "Actor that created the team.")
    private String createdBy;

    @Schema(description = "Last modification timestamp (UTC).", example = "2026-01-01T11:00:00Z")
    private LocalDateTime modifiedAt;

    @Schema(description = "Actor that last modified the team.")
    private String modifiedBy;

    @Schema(description = "Timestamp when the team was trashed (or absent if active).", example = "2026-01-02T12:00:00Z")
    private LocalDateTime trashedAt;

    @Schema(description = "Actor that trashed the team.")
    private String trashedBy;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}

