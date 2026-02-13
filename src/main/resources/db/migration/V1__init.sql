-- Initial schema for person-service
-- MariaDB / Flyway V1

CREATE TABLE person (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    given_name VARCHAR(120) NULL,
    family_name VARCHAR(120) NULL,
    display_name VARCHAR(240) NULL,
    email VARCHAR(320) NULL,
    phone VARCHAR(64) NULL,
    notes TEXT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    KEY idx_person_company_id (company_id),
    KEY idx_person_company_trashed_at (company_id, trashed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE person_tag (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    tag VARCHAR(100) NOT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_person_tag_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_person_tag_company_person_tag UNIQUE (company_id, person_id, tag),
    KEY idx_person_tag_company_id (company_id),
    KEY idx_person_tag_company_trashed_at (company_id, trashed_at),
    KEY idx_person_tag_person_id (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE team (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    name VARCHAR(160) NOT NULL,
    description TEXT NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_team_company_name UNIQUE (company_id, name),
    KEY idx_team_company_id (company_id),
    KEY idx_team_company_trashed_at (company_id, trashed_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE team_member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    team_role VARCHAR(100) NULL,
    joined_at DATETIME(6) NULL,
    left_at DATETIME(6) NULL,
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    created_by VARCHAR(100) NULL,
    modified_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(100) NULL,
    trashed_at DATETIME(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_team_member_team FOREIGN KEY (team_id) REFERENCES team (id),
    CONSTRAINT fk_team_member_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_team_member_team_person UNIQUE (team_id, person_id),
    KEY idx_team_member_company_id (company_id),
    KEY idx_team_member_company_trashed_at (company_id, trashed_at),
    KEY idx_team_member_team_id (team_id),
    KEY idx_team_member_person_id (person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
