package de.innologic.personservice.dto;

import de.innologic.personservice.domain.PersonStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "PersonResponse", description = "Details returned for a person record.")
public class PersonResponse {

    @Schema(description = "Internal database ID for troubleshooting.", example = "42")
    private Long id;

    @Schema(description = "Public person ID (shared with downstream services).", example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String personId;

    @Schema(description = "Tenant (company) identifier.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "First name of the person.", example = "Max")
    private String firstName;

    @Schema(description = "Last name of the person.", example = "Mustermann")
    private String lastName;

    @Schema(description = "Optional display name for UI level shortcuts.", example = "Max Mustermann")
    private String displayName;

    @Schema(description = "Lifecycle status of the person.", requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE")
    private PersonStatus status;

    @Schema(description = "Optional free-text notes.")
    private String notes;

    @Schema(description = "Owner type used for contact-service docking (always PERSON).", requiredMode = Schema.RequiredMode.REQUIRED, example = "PERSON")
    private ContactOwnerType contactOwnerType = ContactOwnerType.PERSON;

    @Schema(description = "Owner ID used for contact-service docking (matches the returned personId).", example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contactOwnerId;

    @Schema(description = "Creation timestamp (UTC).", example = "2026-01-01T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Actor that created the record.")
    private String createdBy;

    @Schema(description = "Last modification timestamp (UTC).", example = "2026-01-01T11:00:00")
    private LocalDateTime modifiedAt;

    @Schema(description = "Actor that last modified the record.")
    private String modifiedBy;

    @Schema(description = "When the record was trashed (or absent if active).", example = "2026-01-02T12:00:00")
    private LocalDateTime trashedAt;

    @Schema(description = "Actor that trashed the record.")
    private String trashedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ContactOwnerType getContactOwnerType() {
        return contactOwnerType;
    }

    public void setContactOwnerType(ContactOwnerType contactOwnerType) {
        this.contactOwnerType = contactOwnerType;
    }

    public String getContactOwnerId() {
        return contactOwnerId;
    }

    public void setContactOwnerId(String contactOwnerId) {
        this.contactOwnerId = contactOwnerId;
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
