-- H2-friendly initial schema for person-service tests

CREATE TABLE person (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id VARCHAR(36) NOT NULL,
    first_name VARCHAR(120) NULL,
    last_name VARCHAR(120) NULL,
    display_name VARCHAR(240) NULL,
    notes TEXT NULL,
    public_id CHAR(36) NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NULL,
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100) NULL,
    trashed_at TIMESTAMP(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    KEY idx_person_company_id (company_id),
    KEY idx_person_company_trashed_at (company_id, trashed_at),
    CONSTRAINT uq_person_public_id UNIQUE (public_id)
);

CREATE TABLE person_tag (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    tag VARCHAR(100) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NULL,
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100) NULL,
    trashed_at TIMESTAMP(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_person_tag_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_person_tag_company_person_tag UNIQUE (company_id, person_id, tag),
    KEY idx_person_tag_company_id (company_id),
    KEY idx_person_tag_company_trashed_at (company_id, trashed_at),
    KEY idx_person_tag_person_id (person_id)
);

CREATE TABLE team (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    name VARCHAR(160) NOT NULL,
    description TEXT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NULL,
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100) NULL,
    trashed_at TIMESTAMP(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT uq_team_company_name UNIQUE (company_id, name),
    KEY idx_team_company_id (company_id),
    KEY idx_team_company_trashed_at (company_id, trashed_at)
);

CREATE TABLE team_member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    team_id BIGINT NOT NULL,
    person_id BIGINT NOT NULL,
    team_role VARCHAR(100) NULL,
    joined_at TIMESTAMP(6) NULL,
    left_at TIMESTAMP(6) NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(100) NULL,
    modified_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(100) NULL,
    trashed_at TIMESTAMP(6) NULL,
    trashed_by VARCHAR(100) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_team_member_team FOREIGN KEY (team_id) REFERENCES team (id),
    CONSTRAINT fk_team_member_person FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT uq_team_member_team_person UNIQUE (team_id, person_id),
    KEY idx_team_member_company_id (company_id),
    KEY idx_team_member_company_trashed_at (company_id, trashed_at),
    KEY idx_team_member_team_id (team_id),
    KEY idx_team_member_person_id (person_id)
);
