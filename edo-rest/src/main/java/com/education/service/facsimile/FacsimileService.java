package com.education.service.facsimile;

import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    FacsimileDTO saveFacsimileEntity(String jsonFile);
    FacsimileDTO archiveFacsimile(String jsonFile);
    FilePoolDto saveFacsimile(MultipartFile multipartFile);
}
