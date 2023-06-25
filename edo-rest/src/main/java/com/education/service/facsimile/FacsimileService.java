package com.education.service.facsimile;

import com.education.model.dto.FacsimileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FacsimileDTO saveFacsimile(MultipartFile multipartFile);
}
