-- Introduce classification catalog and assignment records per Pflichtenheft v1.1.

CREATE TABLE person_classification (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    classification_id CHAR(36) NOT NULL,
    classification_key VARCHAR(120) NOT NULL,
    classification_code VARCHAR(120) NOT NULL,
    classification_label VARCHAR(240) NOT NULL,
    active TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    version BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT uq_person_classification_public_id UNIQUE (classification_id),
    CONSTRAINT uq_person_classification_company_key_code UNIQUE (company_id, classification_key, classification_code),
    KEY idx_person_classification_company_id (company_id),
    KEY idx_person_classification_company_trashed_at (company_id, trashed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE person_classification_assignment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    assignment_id CHAR(36) NOT NULL,
    person_id CHAR(36) NOT NULL,
    classification_id CHAR(36) NOT NULL,
    is_primary TINYINT(1) NOT NULL DEFAULT 0,
    valid_from DATETIME(6) NULL,
    valid_to DATETIME(6) NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    version BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    CONSTRAINT uq_person_classification_assignment_public_id UNIQUE (assignment_id),
    CONSTRAINT fk_person_classification_assignment_classification FOREIGN KEY (classification_id)
        REFERENCES person_classification (classification_id),
    CONSTRAINT fk_person_classification_assignment_person FOREIGN KEY (person_id)
        REFERENCES person (public_id),
    KEY idx_person_classification_assignment_company_id (company_id),
    KEY idx_person_classification_assignment_company_trashed_at (company_id, trashed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
