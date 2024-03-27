CREATE TABLE IF NOT EXISTS report
(
    id                      bigserial PRIMARY KEY,
    creation_date           timestamptz                       NOT NULL,
    creator_id              bigint REFERENCES employee (id),
    resolution_id           bigint REFERENCES resolution (id) NOT NULL,
    comment                 varchar(255),
    is_resolution_completed boolean                           NOT NULL
);

comment on column report.id is 'id отчёта';
comment on column report.creation_date is 'Дата создания отчёта';
comment on column report.creator_id is 'id работника, создавшего отчёт';
comment on column report.resolution_id is 'id резолюции, к которой относится отчёт';
comment on column report.comment is 'Комментарий к резолюции';
comment on column report.is_resolution_completed is 'Флаг, показывающий, была ли выполнена резолюция';
