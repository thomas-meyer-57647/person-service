package de.innologic.personservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PersonCommunicationRefsRequest {

    @NotNull
    private List<@NotBlank @Size(max = 100) String> communicationIds;

    public List<String> getCommunicationIds() {
        return communicationIds;
    }

    public void setCommunicationIds(List<String> communicationIds) {
        this.communicationIds = communicationIds;
    }
}
