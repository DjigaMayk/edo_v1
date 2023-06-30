package com.education.service.facsimile;

import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FilePoolDto saveAsFile(MultipartFile multipartFile);
    FacsimileDTO save(String jsonFile);
    boolean isValidate(MultipartFile multipartFile);
}
