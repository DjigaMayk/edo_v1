package com.education.service.report.impl;

import com.education.feign.feign_report.ReportFeignClient;
import com.education.model.dto.ReportDto;
import com.education.service.AbstractService;
import com.education.service.report.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alexey Pshenichny
 */
@Service
public class ReportServiceImpl extends AbstractService<ReportFeignClient, ReportDto> implements ReportService {

    private final ReportFeignClient reportFeignClient;

    public ReportServiceImpl(ReportFeignClient reportFeignClient) {
        super(reportFeignClient);
        this.reportFeignClient = reportFeignClient;
    }

    @Override
    public List<ReportDto> findAllByCreationDateEquals(LocalDate date) {
        return reportFeignClient.findAllByCreationDateEquals(date);
    }

    @Override
    public List<ReportDto> findAllByCreatorId(Long id) {
        return reportFeignClient.findAllByCreatorId(id);
    }

    @Override
    public List<ReportDto> findAllByResolutionId(Long id) {
        return reportFeignClient.findAllByResolutionId(id);
    }

    @Override
    public List<ReportDto> findAllByIsResolutionCompletedFalse() {
        return reportFeignClient.findAllByIsResolutionCompletedFalse();
    }

    @Override
    public List<ReportDto> findAllByIsResolutionCompletedTrue() {
        return reportFeignClient.findAllByIsResolutionCompletedTrue();
    }

}
