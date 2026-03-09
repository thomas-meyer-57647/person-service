package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "PersonClassificationCreateRequest", description = "Payload to add a new classification for a tenant.")
public class PersonClassificationCreateRequest {

    @NotNull
    @Schema(description = "Tenant identifier; must match the companyId in the request path.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @NotBlank
    @Schema(description = "Business key for the classification (e.g. `employmentStatus`).", example = "employmentStatus", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 120)
    private String key;

    @NotBlank
    @Schema(description = "Short code that identifies this classification within its key.", example = "ACTIVE", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 120)
    private String code;

    @NotBlank
    @Schema(description = "Readable label returned in APIs and UIs.", example = "active employee", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 240)
    private String label;

    @Schema(description = "Flag that indicates whether the classification can be assigned.", example = "true")
    private Boolean active = true;

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
}
