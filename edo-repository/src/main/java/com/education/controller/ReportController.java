package com.education.controller;

import com.education.model.dto.ReportDto;
import com.education.service.report.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.apache.logging.log4j.Level;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Alexey Pshenichny
 */
@Tag(name = "Report controller (repository)")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/repository/report")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Получение всех отчётов за указанную дату")
    @GetMapping("/allByCreationDateEquals/{date}")
    public ResponseEntity<List<ReportDto>> getAllByCreationDateEquals(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        List<ReportDto> reports = reportService.findAllByCreationDateEquals(date);
        if (reports.isEmpty()) {
            log.log(Level.WARN, "За {} отчёты не найдены", date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "За {} найдено {} отчётов", date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), reports.size());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех отчётов по id работника")
    @GetMapping("/allByCreatorId/{id}")
    public ResponseEntity<List<ReportDto>> getAllByCreatorId(@PathVariable("id") Long creatorId) {
        List<ReportDto> reports = reportService.findAllByCreatorId(creatorId);
        if (reports.isEmpty()) {
            log.log(Level.WARN, "Отчёты с id работника = {} не найдены", creatorId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Найдено {} отчётов с id работника = {}", reports.size(), creatorId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех отчётов по id резолюции")
    @GetMapping("/allByResolutionId/{id}")
    public ResponseEntity<List<ReportDto>> getAllByResolutionId(@PathVariable("id") Long resolutionId) {
        List<ReportDto> reports = reportService.findAllByResolutionId(resolutionId);
        if (reports.isEmpty()) {
            log.log(Level.WARN, "Отчёты с id резолюции = {} не найдены", resolutionId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Найдено {} отчётов с id резолюции = {}", reports.size(), resolutionId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех отчётов с невыполненным заданием")
    @GetMapping("/allByIsResolutionCompletedFalse")
    public ResponseEntity<List<ReportDto>> getAllByIsResolutionCompletedFalse() {
        List<ReportDto> reports = reportService.findAllByIsResolutionCompletedFalse();
        if (reports.isEmpty()) {
            log.log(Level.WARN, "Отчёты с невыполненным заданием не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Найдено {} отчётов с невыполненным заданием", reports.size());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех отчётов с выполненным заданием")
    @GetMapping("/allByIsResolutionCompletedTrue")
    public ResponseEntity<List<ReportDto>> getAllByIsResolutionCompletedTrue() {
        List<ReportDto> reports = reportService.findAllByIsResolutionCompletedTrue();
        if (reports.isEmpty()) {
            log.log(Level.WARN, "Отчёты с выполненным заданием не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Найдено {} отчётов с выполненным заданием", reports.size());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Получение отчёта по id")
    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(@PathVariable(value = "id") Long id) {
        ReportDto reportDto = reportService.getById(id);
        if (reportDto == null) {
            log.log(Level.WARN, "Отчёт с id = {} не найден", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Отчёт с id = {} найден", id);
        return new ResponseEntity<>(reportDto, HttpStatus.OK);
    }

    @Operation(summary = "Получение всех отчётов")
    @GetMapping("/all")
    public ResponseEntity<List<ReportDto>> getAll() {
        List<ReportDto> reports = reportService.findAll();
        if (reports.isEmpty()) {
            log.log(Level.WARN, "Отчёты не найдены");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.log(Level.INFO, "Найдено {} отчётов", reports.size());
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @Operation(summary = "Сохранение отчёта в БД")
    @PostMapping("/save")
    public ResponseEntity<ReportDto> saveReport(@RequestBody ReportDto reportDto) {
        ReportDto newReportDto = reportService.save(reportDto);
        if (newReportDto == null) {
            log.log(Level.WARN, "Отчёт не был сохранён");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.log(Level.INFO, "Отчёт был успешно сохранён");
        return new ResponseEntity<>(newReportDto, HttpStatus.OK);
    }

    @Operation(summary = "Обновление отчёта в БД")
    @PutMapping("/update")
    public ResponseEntity<ReportDto> updateReport(@RequestBody ReportDto reportDto) {
        ReportDto oldReportDto = reportService.getById(reportDto.getId());
        ReportDto newReportDto = reportService.save(reportDto);
        if (newReportDto == null || oldReportDto == newReportDto) {
            log.log(Level.WARN, "Отчёт не был обновлён");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.log(Level.INFO, "Отчёт был успешно обновлён");
        return new ResponseEntity<>(newReportDto, HttpStatus.OK);
    }

    @Operation(summary = "Удаление отчёта из БД")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable(value = "id") Long id) {
        log.log(Level.INFO, "Отчёт с id = {} удалён", id);
        reportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
