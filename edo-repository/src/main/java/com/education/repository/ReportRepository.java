package com.education.repository;

import com.education.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexey Pshenichny
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    /**
     * Метод осуществляет поиск всех отчётов, созданных за указанную дату
     *
     * @param date дата, по которой осуществляется поиск
     * @return список отчётов, созданных за указанную дату
     */
    @Query(nativeQuery = true, value = "SELECT r.* FROM report r WHERE DATE(r.creation_date) = :date")
    List<Report> findAllByCreationDateEquals(@Param("date") LocalDate date);

    List<Report> findAllByCreatorId(Long id);

    List<Report> findAllByResolutionId(Long id);

    List<Report> findAllByIsResolutionCompletedFalse();

    List<Report> findAllByIsResolutionCompletedTrue();

}
