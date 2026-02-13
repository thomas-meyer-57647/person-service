package de.innologic.personservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "person_communication_ref",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_person_comm_ref_company_person_comm",
                        columnNames = {"company_id", "person_id", "communication_id"}
                )
        }
)
public class PersonCommunicationRef extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "communication_id", nullable = false, length = 100)
    private String communicationId;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(String communicationId) {
        this.communicationId = communicationId;
    }
}
