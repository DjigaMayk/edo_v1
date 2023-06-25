package com.education.service.facsimile.impl;

import com.education.entity.Facsimile;
import com.education.repository.FacsimileRepository;
import com.education.repository.FilePoolRepository;
import com.education.service.facsimile.FacsimileService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Никита Бадеев
 * Сервис-класс для объекта Facsimile
 */
@Service
@RequiredArgsConstructor
public class FacsimileServiceImpl implements FacsimileService {
    /**
     * TODO
     * .Save picture of Facsimile
     * .Picture should be .jpg .JPEG or .png * Done in Controller
     * .Picture should be file in FilePoolDTO * Should be in Controller
     * .Picture shouldn't be converted to PDF
     * .Picture should be 100x100 px
     *
     * .Save picture, check it for extension(.jpg .JPEG or .png), size(100x100px)
     * .make it inconvertible to PDF and make it FilePoolDTO object
     */

    /**
     * Объект класса-репозитория для сущности Facsimile
     */
    private final FacsimileRepository facsimileRepository;

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public void saveFacsimile(Facsimile facsimileTransfered) {
        facsimileRepository.save(facsimileTransfered);
    }

    @Transactional(readOnly = true)
    @Override
    public Facsimile findById(Long id) {
        return facsimileRepository.findById(id).orElse(null);
    }
}
