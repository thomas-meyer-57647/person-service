-- Align company_id columns with final public ID format

ALTER TABLE person ALTER COLUMN company_id VARCHAR(36) NOT NULL;
ALTER TABLE person_tag ALTER COLUMN company_id VARCHAR(36) NOT NULL;
ALTER TABLE team ALTER COLUMN company_id VARCHAR(36) NOT NULL;
ALTER TABLE team_member ALTER COLUMN company_id VARCHAR(36) NOT NULL;
ALTER TABLE person_communication_ref ALTER COLUMN company_id VARCHAR(36) NOT NULL;
