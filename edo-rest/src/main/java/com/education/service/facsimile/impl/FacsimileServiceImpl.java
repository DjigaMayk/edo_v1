package com.education.service.facsimile.impl;

import com.education.feign.feign_facsimile.FacsimileFeignClient;
import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Никита Бадеев
 * Service для сохранения факсимиле
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {
    private final FacsimileFeignClient FEIGN;

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @Override
    public FilePoolDto saveFacsimile(MultipartFile multipartFile) {
        return FEIGN.saveFacsimile(multipartFile);
    }

    /**
     * Method for saving facsimile as Entity in DB
     *
     * @param jsonFile employee and others
     * @return facsimileDTO
     */
    @Override
    public FacsimileDto saveFacsimileEntity(String jsonFile) {
        return FEIGN.saveFacsimileEntity(jsonFile);
    }

    /**
     * Method for archiving/unarchivig facsimile
     *
     * @param jsonFile data with id Facsimile and boolean isArchived
     * @return FacsimileDto
     */
    @Override
    public FacsimileDto archiveFacsimile(String jsonFile) {
        return FEIGN.archiveFacsimile(jsonFile);
    }
}
