package com.education.service.report.impl;

import com.education.entity.Report;
import com.education.model.dto.ReportDto;
import com.education.repository.ReportRepository;
import com.education.service.AbstractService;
import com.education.service.report.ReportService;
import com.education.util.Mapper.impl.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexey Pshenichny
 */
@Service
public class ReportServiceImpl extends AbstractService<ReportRepository, Report, ReportDto, ReportMapper> implements ReportService {

    public ReportServiceImpl(ReportRepository repository, ReportMapper reportMapper) {
        super(repository, reportMapper);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByCreatorId(Long id) {
        List<Report> reports = repository.findAllByCreatorId(id);
        return reports.isEmpty() ? null : mapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByResolutionId(Long id) {
        List<Report> reports = repository.findAllByResolutionId(id);
        return reports.isEmpty() ? null : mapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByIsResolutionCompletedFalse() {
        List<Report> reports = repository.findAllByIsResolutionCompletedFalse();
        return reports.isEmpty() ? null : mapper.toDto(reports);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ReportDto> findAllByIsResolutionCompletedTrue() {
        List<Report> reports = repository.findAllByIsResolutionCompletedTrue();
        return reports.isEmpty() ? null : mapper.toDto(reports);
    }

    @Override
    public ReportDto getById(Long id) {
        Optional<Report> report = repository.findById(id);
        return report.map(mapper::toDto).orElse(null);
    }

    @Override
    public ReportDto save(ReportDto reportDto) {
        Report report = mapper.toEntity(reportDto);
        if (report.getCreationDate() == null) {
            report.setCreationDate(ZonedDateTime.now());
        }
        return mapper.toDto(repository.save(report));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReportDto update(Long id, ReportDto reportDto) {
        Report report = mapper.toEntity(reportDto);
        report.setId(id);
        report.setCreationDate(getById(id).getCreationDate());
        return mapper.toDto(repository.save(report));
    }

}
