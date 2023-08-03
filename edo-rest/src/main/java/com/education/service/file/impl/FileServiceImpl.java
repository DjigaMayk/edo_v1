package com.education.service.file.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.FilePoolDto;
import com.education.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author Дарья Лукьянова
 * Сервис-класс для добавления файла в файловое хранилище (FilePool)
 */
@Log
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    final private FileRestTemplateClient restTemplateClient;

    @Override
    public FilePoolDto uploadFile(MultipartFile multipartFile) {
        return restTemplateClient.uploadFile(multipartFile);
    }
    @Override
    public void loadFile(UUID uuid) {
        restTemplateClient.loadFile(uuid);
    }

}
