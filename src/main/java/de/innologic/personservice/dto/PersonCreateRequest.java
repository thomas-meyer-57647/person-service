package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "PersonCreateRequest", description = "Payload to register a new person record within a tenant.")
public class PersonCreateRequest {

    @NotNull
    @Schema(description = "Tenant (company) ID in whose scope the person is created.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Size(max = 120)
    @Schema(description = "Person's first name.", example = "Max")
    private String firstName;

    @Size(max = 120)
    @Schema(description = "Person's last name.", example = "Mustermann")
    private String lastName;

    @Size(max = 240)
    @Schema(description = "Optional display name, used for UI representations.", example = "Max Mustermann")
    private String displayName;

    @Schema(description = "Optional free-text notes or remarks stored with the person.")
    private String notes;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
