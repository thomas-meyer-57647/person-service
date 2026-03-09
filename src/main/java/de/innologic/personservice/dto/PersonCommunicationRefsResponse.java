package de.innologic.personservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "PersonCommunicationRefsResponse", description = "Current communication reference IDs that belong to the person.")
public class PersonCommunicationRefsResponse {

    @Schema(description = "Tenant (company) identifier that owns the person.", example = "company-123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String companyId;

    @Schema(description = "Public person ID whose refs are reported.", example = "beb65d9f-8f4b-4c1f-9b0d-1c3ffc572123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String personId;

    @Schema(description = "List of contact-service communication reference IDs associated with the person.", example = "[\"c-1\",\"c-2\"]")
    private List<String> communicationIds;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public List<String> getCommunicationIds() {
        return communicationIds;
    }

    public void setCommunicationIds(List<String> communicationIds) {
        this.communicationIds = communicationIds;
    }
}


