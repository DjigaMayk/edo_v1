package com.education.service.facsimile;

import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
//    FacsimileDTO save(MultipartFile multipartFile);
//    FacsimileDTO save(MultipartFile multipartFile, EmployeeDto employeeDto, DepartmentDto departmentDto);

    FacsimileDTO save(MultipartFile multipartFile, String jsonFile);
    boolean isValidate(MultipartFile multipartFile);
}
