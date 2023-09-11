ALTER TABLE IF EXISTS resolution
    ADD COLUMN IF NOT EXISTS is_draft boolean;
comment on column resolution.is_draft is 'Признак черновика резолюции';