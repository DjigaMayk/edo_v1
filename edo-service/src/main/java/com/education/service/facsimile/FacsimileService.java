package com.education.service.facsimile;

import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FilePoolDto saveAsFile(MultipartFile multipartFile);

    FacsimileDTO save(MultipartFile multipartFile);
    boolean isValidate(MultipartFile multipartFile);
}
