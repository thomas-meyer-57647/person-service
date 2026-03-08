-- Introduce stable external identifiers for teams and memberships plus the primary flag.

ALTER TABLE team
    ADD COLUMN team_id CHAR(36) NULL AFTER company_id;

UPDATE team
    SET team_id = UUID()
    WHERE team_id IS NULL;

ALTER TABLE team
    MODIFY COLUMN team_id CHAR(36) NOT NULL;

ALTER TABLE team
    ADD CONSTRAINT uq_team_team_id UNIQUE (team_id);

ALTER TABLE team_member
    ADD COLUMN membership_id CHAR(36) NULL AFTER id,
    ADD COLUMN is_primary TINYINT(1) NOT NULL DEFAULT 0 AFTER left_at;

UPDATE team_member
    SET membership_id = UUID()
    WHERE membership_id IS NULL;

ALTER TABLE team_member
    MODIFY COLUMN membership_id CHAR(36) NOT NULL;

ALTER TABLE team_member
    ADD CONSTRAINT uq_team_member_membership_id UNIQUE (membership_id);
