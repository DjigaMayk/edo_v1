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

    public ReportServiceImpl(ReportFeignClient reportFeignClient) {
        super(reportFeignClient);
    }

    @Override
    public List<ReportDto> findAllByCreationDateEquals(LocalDate date) {
        return feignClient.findAllByCreationDateEquals(date);
    }

    @Override
    public List<ReportDto> findAllByCreatorId(Long id) {
        return feignClient.findAllByCreatorId(id);
    }

    @Override
    public List<ReportDto> findAllByResolutionId(Long id) {
        return feignClient.findAllByResolutionId(id);
    }

    @Override
    public List<ReportDto> findAllByIsResolutionCompletedFalse() {
        return feignClient.findAllByIsResolutionCompletedFalse();
    }

    @Override
    public List<ReportDto> findAllByIsResolutionCompletedTrue() {
        return feignClient.findAllByIsResolutionCompletedTrue();
    }

    @Override
    public ReportDto update(Long id, ReportDto reportDto) {
        return feignClient.update(id, reportDto);
    }

}
