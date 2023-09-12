ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS registration_date timestamptz;

comment on column appeal.registration_date is 'Дата регистрации обращения';