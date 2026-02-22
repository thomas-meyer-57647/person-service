-- Change company_id type from BIGINT/NUMERIC to VARCHAR(36) across all relevant tables

ALTER TABLE person
    MODIFY COLUMN company_id VARCHAR(36) NOT NULL;

ALTER TABLE person_tag
    MODIFY COLUMN company_id VARCHAR(36) NOT NULL;

ALTER TABLE team
    MODIFY COLUMN company_id VARCHAR(36) NOT NULL;

ALTER TABLE team_member
    MODIFY COLUMN company_id VARCHAR(36) NOT NULL;

ALTER TABLE person_communication_ref
    MODIFY COLUMN company_id VARCHAR(36) NOT NULL;
