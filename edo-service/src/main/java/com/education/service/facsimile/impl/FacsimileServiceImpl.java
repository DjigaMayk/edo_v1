package com.education.service.facsimile.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import com.education.model.enumEntity.EnumFileType;
import com.education.service.department.DepartmentService;
import com.education.service.employee.EmployeeRestTemplateService;
import com.education.service.facsimile.FacsimileService;
import com.education.service.file_pool.FilePoolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class FacsimileServiceImpl implements FacsimileService {

    /**
     * Client for sending and receiving requests
     */
    private final RestTemplate TEMPLATE;

    /**
     * Client for getting instance
     */
    private final EurekaClient EUREKA_CLIENT;

    /**
     * Path to the repository rest-controller
     */
    private static final String BASE_URL = "/api/repository/facsimile";

    /**
     * Name of the microservice we are trying to access
     */
    private final String SERVICE_NAME = "edo-repository";

    /**
     * Service and client for saving facsimile in storage
     */
    private final FilePoolService filePoolService;
    private final FileRestTemplateClient fileRestTemplateClient;
    private final EmployeeRestTemplateService employeeRestTemplateService;
    private final DepartmentService departmentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
    @Override
    public FilePoolDto saveAsFile(MultipartFile multipartFile) {
        try {
            var file = fileRestTemplateClient.saveFile(multipartFile.getBytes());
            return filePoolService.add(FilePoolDto.builder()
                    .storageFileId(file)
                    .name(multipartFile.getOriginalFilename())
                    .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                    .fileType(EnumFileType.FACSIMILE)
                    .size((multipartFile.getBytes()).length)
                    .pageCount(1)
                    .creator(EmployeeDto.builder().id(1L).build())//TODO Переделать на нормального Employee
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for saving facsimile as Entity in DB
     *
     * @param jsonFile employee and others
     * @return facsimileDTO
     */
    @Override
    public FacsimileDTO save(String jsonFile) {

        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            EmployeeDto employee = objectMapper.treeToValue(jsonNode.get("employee"), EmployeeDto.class);
            DepartmentDto department = objectMapper.treeToValue(jsonNode.get("department"), DepartmentDto.class);
            FilePoolDto filePool = objectMapper.treeToValue(jsonNode.get("file_pool"), FilePoolDto.class);

            FacsimileDTO facsimileDTO = FacsimileDTO.builder()
                    .employee(employee)
                    .department(department)
                    .file(filePool)
                    .isArchived(false)
                    .build();
            var request = new RequestEntity<>(facsimileDTO, HttpMethod.POST, uri);
            return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for archiving/unarchivig facsimile
     *
     * @param jsonFile data with id Facsimile and boolean isArchived
     * @return FacsimileDto
     */
    @Override
    public FacsimileDTO archiveFacsimile(String jsonFile) {
        String lastPathComponent = "/archive";
        URI uri = generateUri(this.getInstance(), lastPathComponent);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            FacsimileDTO facsimileFromJson = objectMapper.treeToValue(jsonNode.get("facsimile"), FacsimileDTO.class);
            FacsimileDTO facsimileDTO = getById(facsimileFromJson.getId());
            facsimileDTO.setArchived(facsimileFromJson.isArchived());
            filePoolService.moveToArchive(facsimileDTO.getFile().getId());

            var request = new RequestEntity<>(facsimileDTO, HttpMethod.DELETE, uri);
            return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for getting facsimile by id
     * @param id Long
     *
     * @return FacsimileDto
     */
    @Override
    public FacsimileDTO getById(Long id) {
        String lastPathName = "/" + id;
        URI uri = generateUri(this.getInstance(), lastPathName);
        RequestEntity<Object> request = new RequestEntity<>(null, HttpMethod.GET, uri);
        return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
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
            InputStream inputStream = multipartFile.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
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
