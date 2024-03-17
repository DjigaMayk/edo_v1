ALTER TABLE IF EXISTS resolution
    ADD COLUMN IF NOT EXISTS parent_resolution_id   bigint  references resolution (id);