CREATE TABLE person_communication_ref (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    communication_id VARCHAR(100) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_person_communication_ref_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_person_comm_ref_company_person_comm UNIQUE (company_id, person_id, communication_id),
    KEY idx_person_comm_ref_company_id (company_id),
    KEY idx_person_comm_ref_company_trashed_at (company_id, trashed_at),
    KEY idx_person_comm_ref_person_id (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
