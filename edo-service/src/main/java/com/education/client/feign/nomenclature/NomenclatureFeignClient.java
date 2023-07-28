package com.education.client.feign.nomenclature;

import com.education.model.dto.NomenclatureDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс NomenclatureFeignClient предоставляет методы для отправки запросов в edo-repository
 * через FeignClient.
 */
@FeignClient(name = "edo-repository", path = "/api/repository", qualifiers = "nomenclatureFeignClient")
public interface NomenclatureFeignClient {

    @PostMapping("/nomenclature/")
    NomenclatureDto saveNomenclature(@RequestBody NomenclatureDto nomenclature);

    @GetMapping("/nomenclature/{id}")
    NomenclatureDto findByIdNomenclature(@PathVariable("id") Long id);

    @PostMapping("/nomenclature/findAll")
    List<NomenclatureDto> findAllByIdNomenclature(@RequestBody List<Long> ids);

    @GetMapping("/nomenclature/notArchived/{id}")
    NomenclatureDto findByIdNotArchivedNomenclature(@PathVariable("id") Long id);

    @PostMapping("/nomenclature/notArchived")
    List<NomenclatureDto> findAllByIdNotArchivedNomenclature(@RequestBody List<Long> ids);

    @PatchMapping("/nomenclature/archived/{id}")
    void moveToArchiveNomenclature(@PathVariable("id") Long id);

    @GetMapping("/nomenclature/search/")
    List<NomenclatureDto> findByIndex(@RequestParam("index") String index);
}
