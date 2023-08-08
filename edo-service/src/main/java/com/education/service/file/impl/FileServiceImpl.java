package com.education.service.file.impl;

import com.education.client.FacsimileRestTemplateClient;
import com.education.client.FileRestTemplateClient;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.file.FileService;
import com.education.service.file_pool.FilePoolService;
import com.education.utils.fileConvertion.FacsimileOverlayService;
import com.education.utils.fileConvertion.FileConversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import java.util.UUID;

/**
 * Сервис класс для реализации операций над загружаемым файлом.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class FileServiceImpl implements FileService {

    private final FilePoolService filePoolService;
    private final FileConversionService fileConversionService;
    private final FacsimileOverlayService facsimileOverlayService;
    private final FileRestTemplateClient fileRestTemplateClient;
    private final FacsimileRestTemplateClient facsimileRestTemplateClient;
    /**
     * Метод сохраняет полученный файл в файловое хранилище,
     * предварительно сконвертировав его в pdf и наложив facsimile,
     * создает и возвращает FilePoolDto.
     * Проверить функционал можно следующим образом:
     * - отправить POST-запрос через Postman на http://127.0.0.1:8080/api/rest/file, приложив загружаемый file во вкладке Body.
     *      В ответ получаем JSON с необходимым UUID документа, который загрузили.
     * - отправить GET-запрос на http://127.0.0.1:8080/api/rest/file/{UUID документа}
     * - проверить в корневой папке наличие файла MYPDF.pdf
     */
    @Override
    public FilePoolDto saveFile(MultipartFile multipartFile) {

        // В будущем нужно реализовать вытягивание id факсимиле из БД на основе авторизованного employee

        FacsimileDto facsimile = facsimileRestTemplateClient.getFacsimileByEmployeeId(1L);
        log.info("Получили необходимый факсимиле");

        Map<String, Object> convertedFile = fileConversionService.convertFile(multipartFile);

        Map<String, Object> overlayedFile = facsimileOverlayService.overlay(convertedFile, getFileByUUID(facsimile.getFile().getStorageFileId()));
        log.info("Наложение факсимиле завершено");


        var savedFileUUID = fileRestTemplateClient.saveFile((byte[]) overlayedFile.get("file"));

        return filePoolService.add(
                FilePoolDto.builder()
                        .storageFileId(savedFileUUID)
                        .name(multipartFile.getOriginalFilename())
                        .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                        .size(((byte[]) overlayedFile.get("file")).length)
                        .pageCount((int) overlayedFile.get("pageCount"))
                        .creator(getCreatorFromSecurity())
                        .build()
        );
    }

    /**
     * Метод возвращает файл из файлового хранилища.
     * @param uuid - название файла в файловом хранилище.
     * @return файл в виде byte[].
     */
    @Override
    public byte[] getFileByUUID(UUID uuid) {
        return fileRestTemplateClient.getFile(uuid);
    }

    /**
     * Метод возвращает EmployeeDto аутентифицированного пользователя.
     */
    private EmployeeDto getCreatorFromSecurity() {
        return EmployeeDto.builder()
                .id(1L)
                .build();
    }
}