-- H2-friendly communication references table for tests

CREATE TABLE person_communication_ref (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    communication_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NULL,
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100) NULL,
    trashed_at TIMESTAMP(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_person_communication_ref_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_person_comm_ref_company_person_comm UNIQUE (company_id, person_id, communication_id),
    KEY idx_person_comm_ref_company_id (company_id),
    KEY idx_person_comm_ref_company_trashed_at (company_id, trashed_at),
    KEY idx_person_comm_ref_person_id (person_id)
);
