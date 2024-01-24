ALTER TABLE IF EXISTS approval_sheet
    ADD COLUMN IF NOT EXISTS appeal_id BIGINT REFERENCES appeal (id);

comment on column approval_sheet.appeal_id is 'ID Обращения к которому относиться данный Лист Согласования';