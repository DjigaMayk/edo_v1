package com.education.service.report.impl;

import com.education.entity.Report;
import com.education.model.dto.ReportDto;
import com.education.repository.ReportRepository;
import com.education.service.AbstractService;
import com.education.service.report.ReportService;
import com.education.util.Mapper.impl.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexey Pshenichny
 */
@Service
public class ReportServiceImpl extends AbstractService<ReportRepository, Report, ReportDto, ReportMapper> implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper) {
        super(reportRepository, reportMapper);
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByCreationDateEquals(LocalDate date) {
        List<Report> reports = reportRepository.findAllByCreationDateEquals(date);
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByCreatorId(Long id) {
        List<Report> reports = reportRepository.findAllByCreatorId(id);
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByResolutionId(Long id) {
        List<Report> reports = reportRepository.findAllByResolutionId(id);
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByIsResolutionCompletedFalse() {
        List<Report> reports = reportRepository.findAllByIsResolutionCompletedFalse();
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByIsResolutionCompletedTrue() {
        List<Report> reports = reportRepository.findAllByIsResolutionCompletedTrue();
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ReportDto getById(Long id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.map(reportMapper::toDto).orElse(null);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAll() {
        List<Report> reports = reportRepository.findAll();
        return reports.isEmpty() ? null : reportMapper.toDto(reports);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReportDto save(ReportDto reportDto) {
        Report report = reportMapper.toEntity(reportDto);
        if (report.getCreationDate() == null) {
            report.setCreationDate(ZonedDateTime.now());
        }
        return reportMapper.toDto(reportRepository.save(report));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReportDto update(ReportDto reportDto) {
        Report report = reportMapper.toEntity(reportDto);
        return reportMapper.toDto(reportRepository.save(report));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }

}
