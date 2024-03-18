package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agreement_participant")
@Setter
@Getter
@ToString
public class AgreementParticipant {

    public enum ParticipantType {
        INITIATOR, PARTICIPANT, SIGNATORY
    }
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Тип участника
     */
    @Column(name = "participant_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ParticipantType participantType;

    /**
     * Дата создания
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Срок выполнения
     */
    @Column(name = "due_date", nullable = false)
    private ZonedDateTime dueDate;

    /**
     * Дата получения
     */
    @Column(name = "receipt_date", nullable = false)
    private ZonedDateTime receiptDate;

    /**
     * Дата завершения
     */
    @Column(name = "completion_date", nullable = false)
    private ZonedDateTime completionDate;

    /**
     * Номер по порядку согласования
     */
    @Column(name = "approval_order", nullable = false)
    private int approvalOrder;

    /**
     * Порядок отображения
     */
    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    /**
     * Связь с объектом Employee
     */
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
