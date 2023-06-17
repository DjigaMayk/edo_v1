package com.education.service.facsimile;


import com.education.entity.Facsimile;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService {
    Facsimile findById(Long id);
    void saveFacsimile(Facsimile facsimileTransfered);
}
