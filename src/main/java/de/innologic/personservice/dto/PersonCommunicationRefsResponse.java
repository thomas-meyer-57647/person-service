package de.innologic.personservice.dto;

import java.util.List;

public class PersonCommunicationRefsResponse {

    private Long companyId;
    private Long personId;
    private List<String> communicationIds;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public List<String> getCommunicationIds() {
        return communicationIds;
    }

    public void setCommunicationIds(List<String> communicationIds) {
        this.communicationIds = communicationIds;
    }
}
