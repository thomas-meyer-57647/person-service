package de.innologic.personservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;

@Entity
@Table(name = "person_classification", uniqueConstraints = {
        @UniqueConstraint(name = "uq_person_classification_company_key_code",
                columnNames = {"company_id", "classification_key", "classification_code"})
})
public class PersonClassification extends BaseEntity {

    @Column(name = "classification_id", length = 36, nullable = false, unique = true)
    private String classificationId;

    @Column(name = "classification_key", length = 120, nullable = false)
    private String key;

    @Column(name = "classification_code", length = 120, nullable = false)
    private String code;

    @Column(name = "classification_label", length = 240, nullable = false)
    private String label;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
