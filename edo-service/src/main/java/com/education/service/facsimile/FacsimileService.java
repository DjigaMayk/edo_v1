package com.education.service.facsimile;

import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FacsimileDto getFacsimileByEmployeeId(Long id);

    FilePoolDto saveAsFile(MultipartFile multipartFile);

    FacsimileDto save(String jsonFile);

    FacsimileDto archiveFacsimile(String jsonFile);

    FacsimileDto getById(Long id);

    boolean isValidate(MultipartFile multipartFile);
}
