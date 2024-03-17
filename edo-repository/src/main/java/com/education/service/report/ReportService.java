package com.education.service.report;

import com.education.model.dto.ReportDto;
import com.education.service.BaseInterface;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexey Pshenichny
 */
public interface ReportService extends BaseInterface<ReportDto> {

    List<ReportDto> findAllByCreationDateEquals(LocalDate date);

    List<ReportDto> findAllByCreatorId(Long id);

    List<ReportDto> findAllByResolutionId(Long id);

    List<ReportDto> findAllByIsResolutionCompletedFalse();

    List<ReportDto> findAllByIsResolutionCompletedTrue();

    ReportDto update(ReportDto reportDto);

}
