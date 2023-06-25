package com.education.service.facsimile.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.DepartmentDto;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;

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
     * Путь до рест контроллера репозитория
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
     *
     * @param multipartFile facsimile
     * @return facsimileDTO
     */
    @Override
    public FacsimileDTO save(MultipartFile multipartFile) {
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);

        FacsimileDTO facsimileDTO = FacsimileDTO.builder()                                                              //Создание FacsimileDTO TODO удалить коммент
                .employee(getCreatorFromSecurity())
                .department(new DepartmentDto())
                .file(saveAsFile(multipartFile))
                .isArchived(false)
                .build();

        var request = new RequestEntity<>(facsimileDTO, HttpMethod.POST, uri);
        return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
    }

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @Override
    public FilePoolDto saveAsFile(MultipartFile multipartFile) {
        try {
            File fileDotFacsimile = setTypeFacsimile(multipartFile);                                                    //Установка типа Facsimile файлу TODO удалить коммент
            byte[] fileSizeAtBytes = Files.readAllBytes(fileDotFacsimile.toPath());
            var file = fileRestTemplateClient.saveFile(fileSizeAtBytes);                                                //Сохранение в хранилище TODO удалить коммент
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
     * Method returning authenticated employee as EmployeeDto
     */
    private EmployeeDto getCreatorFromSecurity() {
        return EmployeeDto.builder()
                .id(1L)
                .build();
    }

    /**
     * Method for set type FACSIMILE to file
     *
     * @param multipartFile - facsimile
     * @return facsimile.FACSIMILE File
     */
    private File setTypeFacsimile(MultipartFile multipartFile) {
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        return new File(multipartFile.getOriginalFilename().replace(fileExtension, "FACSIMILE"));
    }

    /**
     * Method for validate
     *
     * @param multipartFile - file to validate
     * @return is file validate or not
     */
    public boolean isValidate(MultipartFile multipartFile) {

        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!(fileExtension.equalsIgnoreCase("jpg")
                || fileExtension.equalsIgnoreCase("jpeg")
                || fileExtension.equalsIgnoreCase("png"))) {
            return false;
        }

        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image.getWidth() > 100 || image.getHeight() > 100) {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    /**
     * Method for getting instance
     *
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
