package com.education.service.department.feign;

import com.education.model.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign Client для взаимодействия с микросервисом "edo-repository",
 */
@FeignClient(name = "edo-repository")
public interface DepartmentFeignService {

    /**
     * Создает новую запись объекта DepartmentDto в базе данных через REST API.
     */
    @PostMapping("/api/repository/department")
    DepartmentDto save(DepartmentDto obj);

    /**
     * Перемещает запись с указанным идентификатором в архив через REST API.
     */
    @DeleteMapping("/api/repository/department/{id}")
    void moveToArchive(@PathVariable("id") Long id);

    /**
     * Получает запись объекта типа DepartmentDto с указанным идентификатором из базы данных через REST API.
     */
    @GetMapping("/api/repository/department/{id}")
    DepartmentDto findById(@PathVariable("id") Long id);

    /**
     * Получает не заархивированную запись объекта типа DepartmentDto с указанным идентификатором
     * из базы данных через REST API.
     */
    @GetMapping("/api/repository/department/notArchived/{id}")
    DepartmentDto findByIdNotArchived(@PathVariable("id") Long id);

    /**
     * Получает список записей объектов типа DepartmentDto по списку идентификаторов из базы данных
     * через REST API.
     */
    @PostMapping("/api/repository/department/findAll")
    List<DepartmentDto> findAllById(@RequestBody List<Long> ids);

    /**
     * Получает список не заархивированных записей объектов типа DepartmentDto по списку идентификаторов
     * из базы данных через REST API.
     */
    @PostMapping("/api/repository/department/findAll/notArchived")
    List<DepartmentDto> findAllByIdNotArchived(@RequestBody List<Long> ids);

    @DeleteMapping("/api/repository/department/{id}")
    void delete(@PathVariable("id") Long id);
}
