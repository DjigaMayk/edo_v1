CREATE TABLE IF NOT EXISTS approval_block
(
    id                  bigserial NOT NULL PRIMARY KEY,
    sequence_number     integer CHECK (sequence_number > 0),
    appeal_id           BIGINT REFERENCES appeal (id),
    approval_block_type varchar
);

comment on column approval_block.id is 'ID обращения';
comment on column approval_block.sequence_number is 'Порядковый номер Блока согласования';
comment on column approval_block.appeal_id is 'Лист согласования в блоке согласования';
comment on column approval_block.approval_block_type is 'Тип блока согласования';