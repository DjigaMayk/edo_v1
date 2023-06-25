package com.education.service.facsimile.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import com.education.service.file_pool.FilePoolService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log
public class FacsimileServiceImpl implements FacsimileService {


    /**
     * Клиент для отправки и получения запросов
     */
    private final RestTemplate TEMPLATE;

    /**
     * Клиент для получения instance
     */
    private final EurekaClient EUREKA_CLIENT;

    /**
     * путь до рест контроллера репозитория
     */
    private static final String BASE_URL = "/api/repository/facsimile";

    /**
     * Название микросервиса, к которому мы пытаемся получить доступ
     */
    private final String SERVICE_NAME = "edo-repository";

    /**
     * Service and client for saving facsimile in storage
     */
    private final FilePoolService filePoolService;
    private final FileRestTemplateClient fileRestTemplateClient;

    /**
     * Method for saving facsimile in DB
     * @param multipartFile facsimile
     * @return facsimileDTO
     */
    @Override
    public FacsimileDTO save(MultipartFile multipartFile) {                                                             //TODO Достать FacsimileDTO из multipart файла
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);
        var request = new RequestEntity<>(multipartFile, HttpMethod.POST, uri);                                         //TODO Сделать FacsimileDTO вместо multipartFile

        return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
    }

    /**
     * Method for saving Facsimile in file-storage
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @Override
    public FilePoolDto saveAsFile(MultipartFile multipartFile) {
        try {
            var file = fileRestTemplateClient.saveFile(multipartFile.getBytes());                                       //Сохранение в хранилище TODO удалить коммент
            return filePoolService.add(                                                                                 //Создание FilePoolDTO TODO удалить коммент
                    FilePoolDto.builder()
                            .storageFileId(file)
                            .name(multipartFile.getOriginalFilename())
                            .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                            .size((multipartFile.getBytes()).length)
                            .pageCount(1)
                            .creator(getCreatorFromSecurity())
                            .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method returning file from file-storage
     *
     * @param uuid - filename
     * @return file as byte[]
     */
    @Override
    public byte[] getFileByUUID(UUID uuid) {
        return fileRestTemplateClient.getFile(uuid);
    }

    /**
     * Method returning authenticated employee as EmployeeDto
     */
    private EmployeeDto getCreatorFromSecurity() {
        return EmployeeDto.builder()
                .id(1L)
                .build();
    }

    /**
     * Method for getting instance
     * @return instance
     */

    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        InstanceInfo instance = instances.get((int) (Math.random() * instances.size()));
        log.info(String.valueOf(instance.getPort()));
        return instance;
    }

    /**
     * Method for generation Uri
     *
     * @param instance InstanceInfo
     * @param path     String
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme("http")
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }
}
