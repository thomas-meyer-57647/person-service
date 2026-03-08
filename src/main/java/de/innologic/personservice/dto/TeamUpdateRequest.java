package de.innologic.personservice.dto;

import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TeamUpdateRequest", description = "Fields that can be updated on an existing team.")
public class TeamUpdateRequest {

    @Size(max = 160)
    @Schema(description = "New name for the team; must stay unique inside the tenant.", example = "Platform Engineering")
    private String name;

    @Schema(description = "New description for the team.", example = "Platform team responsible for automation.")
    private String description;

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
