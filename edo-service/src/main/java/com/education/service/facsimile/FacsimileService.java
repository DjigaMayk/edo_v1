package com.education.service.facsimile;

import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FacsimileDto findById(Long id);
    FilePoolDto saveAsFile(MultipartFile multipartFile);
    FacsimileDto save(String jsonFile);
    FacsimileDto archiveFacsimile(String jsonFile);
    boolean isValidate(MultipartFile multipartFile);
}
