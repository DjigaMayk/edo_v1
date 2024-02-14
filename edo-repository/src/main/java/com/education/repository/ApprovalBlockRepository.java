package com.education.repository;

import com.education.entity.ApprovalBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalBlockRepository extends JpaRepository<ApprovalBlock, Long> {

}
