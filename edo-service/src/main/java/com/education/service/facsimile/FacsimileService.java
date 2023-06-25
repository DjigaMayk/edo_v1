package com.education.service.facsimile;

import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FacsimileService {
    FilePoolDto saveAsFile(MultipartFile multipartFile);

    public byte[] getFileByUUID(UUID uuid);

    FacsimileDTO save(MultipartFile multipartFile);
}
