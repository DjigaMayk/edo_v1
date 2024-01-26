package com.education.service.department;

import com.education.model.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto save(DepartmentDto obj);

    void moveToArchive(Long id);

    DepartmentDto findById(Long id);

    DepartmentDto findByIdNotArchived(Long id);

    List<DepartmentDto> findAllById(List<Long> ids);

    List<DepartmentDto> findAllByIdNotArchived(List<Long> ids);

    DepartmentDto findByFullName(String fullName);
}
