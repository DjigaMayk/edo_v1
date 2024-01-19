package com.education.entity;

import com.education.model.enumEntity.EnumApprovalBlockType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * Сущность: блок согласования.
 *
 * @author Denis Maslov
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "approval_block")
public class ApprovalBlock extends BaseEntity {

    /**
     * Лист согласования в блоке согласования
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appeal_id")
    private ApprovalSheet approvalSheet;

    /**
     * Тип блока согласования
     */
    @Column(name = "approval_block_type")
    @Enumerated(EnumType.STRING)
    private EnumApprovalBlockType approvalBlockType;
}
