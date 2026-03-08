package de.innologic.personservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "TeamMemberAddRequest", description = "Request to add a person to a specific team.")
public class TeamMemberAddRequest {

    @NotBlank
    @Schema(description = "Public personId that identifies the person to add.", example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String personId;

    @Size(max = 100)
    @Schema(description = "Optional role or title for the member within the team.", example = "Developer")
    private String role;

    @Schema(description = "UTC timestamp when the person joined the team; defaults to the current timestamp when omitted.", example = "2026-01-01T10:00:00")
    @PastOrPresent(message = "joinedAt must be in the past or present")
    private LocalDateTime joinedAt;

    @Schema(description = "Optional indicator if this membership represents the person's primary team.", example = "true")
    private Boolean isPrimary;

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

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }
}
