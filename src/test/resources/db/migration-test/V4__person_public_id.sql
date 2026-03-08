-- Add stable public identifier column

ALTER TABLE person ADD COLUMN IF NOT EXISTS public_id CHAR(36) NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uq_person_public_id ON person (public_id);
