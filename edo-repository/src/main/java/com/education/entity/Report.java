package com.education.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;

/**
 * Представляет отчёт по резолюции
 *
 * @author Alexey Pshenichny
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Table(name = "report")
public class Report extends BaseEntity {

    /**
     * Дата создания отчёта
     */
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    /**
     * Работник, создавший отчёт
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Employee creator;

    /**
     * Резолюция, к которой относится отчёт
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resolution_id", nullable = false)
    private Resolution resolution;

    /**
     * Комментарий к резолюции
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Флаг, показывающий, была ли выполнена резолюция
     */
    @Column(name = "is_resolution_completed", nullable = false)
    private Boolean isResolutionCompleted;

}
