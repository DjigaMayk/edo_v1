package com.education.feign.feign_appeal;

import com.education.model.dto.AppealAbbreviatedDto;
import com.education.model.dto.AppealDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "edo-repository", path = "/api/repository/appeal")
public interface AppealFeignService {

    @PostMapping
    AppealDto save(AppealDto appeal);

    @PutMapping("/toArchive/{id}")
    void moveToArchive(@PathVariable("id") Long id);

    @GetMapping(value = "/byId/{id}")
    AppealDto findById(@PathVariable("id") Long id);

    @GetMapping(value = "/allById/{ids}")
    List<AppealDto> findAllById(@PathVariable("ids") Iterable<Long> ids);

    @GetMapping(value = "/notArchived/{id}")
    AppealDto findByIdNotArchived(@PathVariable("id") Long id);

    @GetMapping(value = "/allNotArchived/{ids}")
    List<AppealDto> findAllByIdNotArchived(@PathVariable("ids") Iterable<Long> ids);

    @GetMapping(value = "/appealsByEmployee/")
    List<AppealAbbreviatedDto> findAllByIdEmployee(@RequestParam("startIndex") Long startIndex, @RequestParam("amount") Long amount);

    @GetMapping(value = "/byQuestionId/{questionId}")
    AppealDto findByQuestionId(@PathVariable("questionId") Long questionId);

    @GetMapping(value = "byResolutionId/{resolutionId}")
    AppealDto findAppealByResolutionId(@PathVariable("resolutionId") Long resolutionId);

    @PutMapping("/toNewOrRegistered/")
    void moveToNewOrRegistered(@RequestParam("id") Long id,
                          @RequestParam("appealStatus") String appealStatus);
}
