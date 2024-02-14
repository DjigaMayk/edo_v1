package com.education.service.department.feign;

import com.education.Utils.QuestionUtil;
import com.education.model.dto.DepartmentDto;
import com.education.service.department.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Сервис для контроллера, работает с DepartmentDto
 */
@Service
@RequiredArgsConstructor
@Log
public class DepartmentFeignServiceImpl implements DepartmentService {

    /**
     * Клиент для отправки и получения запросов к микросервису "edo-repository".
     */
    private final DepartmentFeignService departmentFeignService;

    @Override
    public DepartmentDto save(DepartmentDto obj) {
        return departmentFeignService.save(obj);
    }

    @Override
    public void moveToArchive(Long id) {
        departmentFeignService.moveToArchive(id);
    }

    @Override
    public DepartmentDto findById(Long id) {
        return departmentFeignService.findById(id);
    }

    @Override
    public DepartmentDto findByIdNotArchived(Long id) {
        return departmentFeignService.findByIdNotArchived(id);
    }

    @Override
    public List<DepartmentDto> findAllById(List<Long> ids) {
        return departmentFeignService.findAllById(ids);
    }

    @Override
    public List<DepartmentDto> findAllByIdNotArchived(List<Long> ids) {
        return departmentFeignService.findAllByIdNotArchived(ids);
    }

    @Override
    public DepartmentDto findByFullName(String fullName) {
        if (fullName.matches("[а-яА-Я]")) {
            return departmentFeignService.findByFullName(fullName);
        }
        String translitDepartment = QuestionUtil.textTransformer(fullName);
        return departmentFeignService.findByFullName(translitDepartment);
    }

}