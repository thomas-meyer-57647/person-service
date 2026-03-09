package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(name = "PersonCommunicationRefsRequest", description = "Payload that replaces all communication reference IDs for a person.")
public class PersonCommunicationRefsRequest {

    @NotNull
    @Schema(description = "Complete list of communication reference IDs that should remain attached to the person.", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"c-1\",\"c-2\"]")
    private List<@NotBlank @Size(max = 100) String> communicationIds;

    public List<String> getCommunicationIds() {
        return communicationIds;
    }

    public void setCommunicationIds(List<String> communicationIds) {
        this.communicationIds = communicationIds;
    }
}
