-- Adjust person schema to align with the Pflichtenheft (first/last name, status, optimistic locking).

ALTER TABLE person
    CHANGE COLUMN given_name first_name VARCHAR(120) NULL,
    CHANGE COLUMN family_name last_name VARCHAR(120) NULL;

ALTER TABLE person
    DROP COLUMN email,
    DROP COLUMN phone;

ALTER TABLE person
    ADD COLUMN status VARCHAR(32) NOT NULL DEFAULT 'ACTIVE',
    ADD COLUMN version BIGINT NOT NULL DEFAULT 0;
