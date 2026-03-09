package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(name = "PersonClassificationUpdateRequest", description = "Partial payload to update a classification.")
public class PersonClassificationUpdateRequest {

    @Schema(description = "New business key for the classification (optional).", example = "employmentStatus")
    @Size(max = 120)
    private String key;

    @Schema(description = "New code that identifies the classification within its key (optional).", example = "ACTIVE")
    @Size(max = 120)
    private String code;

    @Schema(description = "Updated display label (optional).", example = "Active Employee")
    @Size(max = 240)
    private String label;

    @Schema(description = "Set to false to deactivate the classification without deleting it.", example = "false")
    private Boolean active;

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
}
