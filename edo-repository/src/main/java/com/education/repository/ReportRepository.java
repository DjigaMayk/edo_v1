package com.education.repository;

import com.education.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Pshenichny
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByCreatorId(Long id);

    List<Report> findAllByResolutionId(Long id);

    List<Report> findAllByIsResolutionCompletedFalse();

    List<Report> findAllByIsResolutionCompletedTrue();

}
