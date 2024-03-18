CREATE TABLE IF NOT EXISTS agreement_participant (
    id SERIAL NOT NULL PRIMARY KEY,
    participant_type VARCHAR(20) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    due_date TIMESTAMP WITH TIME ZONE,
    receipt_date TIMESTAMP WITH TIME ZONE,
    completion_date TIMESTAMP WITH TIME ZONE,
    approval_order INT NOT NULL,
    display_order INT NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);

comment on column agreement_participant.id is 'Идентификатор';
comment on column agreement_participant.participant_type is 'Тип участника';
comment on column agreement_participant.creation_date is 'Дата создания';
comment on column agreement_participant.due_date is 'Срок выполнения';
comment on column agreement_participant.receipt_date is 'Дата получения';
comment on column agreement_participant.completion_date is 'Дата выполнения';
comment on column agreement_participant.approval_order is 'Порядок согласования';
comment on column agreement_participant.display_order is 'Порядок отображения';
comment on column agreement_participant.employee_id is 'Идентификатор связанного сотрудника';