package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TeamMemberResponse", description = "Membership details returned for a team.")
public class TeamMemberResponse {

    @Schema(description = "Public membership identifier.", example = "membership-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String membershipId;

    @Schema(description = "Tenant (company) identifier that owns the membership.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "Public team ID that hosts this membership.", example = "team-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String teamId;

    @Schema(description = "Public person ID that participates in the team.", example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String personId;

    @Schema(description = "Role or designation for the member inside the team.", example = "Developer")
    private String role;

    @Schema(description = "Timestamp when the person joined the team.", example = "2026-01-01T10:00:00Z")
    private LocalDateTime joinedAt;

    @Schema(description = "Timestamp when the membership ended.", example = "2026-01-02T12:00:00Z")
    private LocalDateTime leftAt;

    @Schema(description = "Flag that indicates whether this is the person's primary team.", example = "true")
    private Boolean isPrimary;

    @Schema(description = "Creation timestamp (UTC).", example = "2026-01-01T10:00:00Z")
    private LocalDateTime createdAt;

    @Schema(description = "Actor that created the membership.")
    private String createdBy;

    @Schema(description = "Last modification timestamp (UTC).", example = "2026-01-01T11:00:00Z")
    private LocalDateTime modifiedAt;

    @Schema(description = "Actor that last modified the membership.")
    private String modifiedBy;

    @Schema(description = "Timestamp when the membership was trashed (or absent if active).", example = "2026-01-02T12:00:00Z")
    private LocalDateTime trashedAt;

    @Schema(description = "Actor that trashed the membership.")
    private String trashedBy;

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
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
