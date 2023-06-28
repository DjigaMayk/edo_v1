package com.education.service.facsimile;

import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FacsimileDTO saveFacsimile(MultipartFile multipartFile);
    FacsimileDTO saveFacsimile(MultipartFile multipartFile, String jsonFile);
    FacsimileDTO saveFacsimile(MultipartFile facsimile, EmployeeDto employee, DepartmentDto department);
}
