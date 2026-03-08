package de.innologic.personservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TeamCreateRequest", description = "Payload required to create a new tenant-bound team.")
public class TeamCreateRequest {

    @NotNull
    @Schema(description = "Tenant identifier; must match the companyId in the request path.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @NotBlank
    @Schema(description = "Human-readable label for the team, unique inside the tenant.", example = "Platform Engineering", requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(max = 160)
    private String name;

    @Schema(description = "Optional longer description to explain the team's responsibility.", example = "Platform team responsible for shared infrastructure.")
    private String description;

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
}

