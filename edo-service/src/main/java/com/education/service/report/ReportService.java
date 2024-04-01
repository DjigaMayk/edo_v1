package com.education.service.report;

import com.education.model.dto.ReportDto;
import com.education.service.BaseInterface;

import java.util.List;

/**
 * @author Alexey Pshenichny
 */
public interface ReportService extends BaseInterface<ReportDto> {

    List<ReportDto> findAllByCreatorId(Long id);

    List<ReportDto> findAllByResolutionId(Long id);

    List<ReportDto> findAllByIsResolutionCompletedFalse();

    List<ReportDto> findAllByIsResolutionCompletedTrue();

    ReportDto update(Long id, ReportDto reportDto);

}
