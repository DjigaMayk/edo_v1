package com.education.repository;

import com.education.entity.AgreementParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementParticipantRepository extends JpaRepository<AgreementParticipant, Long> {
}
