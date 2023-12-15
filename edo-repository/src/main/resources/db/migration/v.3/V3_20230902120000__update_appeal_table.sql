ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS is_mail_sent BOOLEAN;

comment on column appeal.is_mail_sent is 'Флаг отправки письма';