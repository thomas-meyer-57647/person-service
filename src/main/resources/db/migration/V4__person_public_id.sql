-- Add stable public identifier for person records
-- Safe for empty DBs in DEV setup (no backfill required)

ALTER TABLE person
    ADD COLUMN public_id CHAR(36) NOT NULL;

ALTER TABLE person
    ADD CONSTRAINT uq_person_public_id UNIQUE (public_id);
