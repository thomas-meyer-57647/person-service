package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(name = "PersonUpdateRequest", description = "Fields that can be updated on an existing person record.")
public class PersonUpdateRequest {

    @Size(max = 120)
    @Schema(description = "New first name.", example = "Max")
    private String firstName;

    @Size(max = 120)
    @Schema(description = "New last name.", example = "Mustermann")
    private String lastName;

    @Size(max = 240)
    @Schema(description = "Optional display name override.", example = "Max M.")
    private String displayName;

    @Schema(description = "Optional remarks; will be appended to the person's account notes.")
    private String notes;

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
