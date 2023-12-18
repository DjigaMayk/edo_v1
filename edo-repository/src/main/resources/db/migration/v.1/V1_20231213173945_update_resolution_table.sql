ALTER TABLE IF EXISTS resolution
    ADD COLUMN IF NOT EXISTS resolution_id   bigint  references resolution (id);