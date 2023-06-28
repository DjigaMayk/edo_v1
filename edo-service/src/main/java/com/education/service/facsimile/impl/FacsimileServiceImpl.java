package com.education.service.facsimile.impl;

import com.education.client.FileRestTemplateClient;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDTO;
import com.education.model.dto.FilePoolDto;
import com.education.service.department.DepartmentService;
import com.education.service.employee.EmployeeRestTemplateService;
import com.education.service.facsimile.FacsimileService;
import com.education.service.file_pool.FilePoolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.swagger.v3.core.util.Json;
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
    private final EmployeeRestTemplateService employeeRestTemplateService;
    private final DepartmentService departmentService;

    /**
     * Method for saving facsimile in DB
     *
     * @param multipartFile facsimile
     * @return facsimileDTO
     */


    @Override
    public FacsimileDTO save(MultipartFile multipartFile, String jsonFile) {
        String lastPathComponent = "/";
        URI uri = generateUri(this.getInstance(), lastPathComponent);

        ObjectMapper objectMapper = new ObjectMapper(); //TODO Сделать отдельным методом
        try {
            var file = fileRestTemplateClient.saveFile(multipartFile.getBytes());

            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            EmployeeDto employeeDto = objectMapper.treeToValue(jsonNode.get("employee"), EmployeeDto.class);
            DepartmentDto departmentDto = objectMapper.treeToValue(jsonNode.get("employee"), DepartmentDto.class);
            FilePoolDto filePoolDto = objectMapper.treeToValue(jsonNode.get("employee"), FilePoolDto.class);

            FacsimileDTO facsimileDTO = FacsimileDTO.builder()
                    .employee(employeeDto)
                    .department(departmentDto)
                    .file(filePoolDto)
                    .isArchived(false)
                    .build();
            var request = new RequestEntity<>(facsimileDTO, HttpMethod.POST, uri);
            return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public FacsimileDTO save(MultipartFile multipartFile) {
//        String lastPathComponent = "/";
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//
//        FacsimileDTO facsimileDTO = FacsimileDTO.builder()
//                .employee(getCreatorFromSecurity())
//                .department(getDepartment())
//                .file(saveAsFile(multipartFile))
//                .file(new FilePoolDto())
//                .isArchived(false)
//                .build();
//
//        var request = new RequestEntity<>(facsimileDTO, HttpMethod.POST, uri);
//        return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
//    }

//    @Override
//    public FacsimileDTO save(MultipartFile multipartFile, EmployeeDto employeeDto, DepartmentDto departmentDto) {
//        String lastPathComponent = "/";
//        URI uri = generateUri(this.getInstance(), lastPathComponent);
//
//        EmployeeDto employee = employeeRestTemplateService.findById(employeeDto.getId(), true);
//        DepartmentDto department = departmentService.findById(departmentDto.getId());
//
//        FacsimileDTO facsimileDTO = FacsimileDTO.builder()
//                .employee(employee)
//                .department(department)
//                .file(saveAsFile(multipartFile))
//                .isArchived(false)
//                .build();
//
//        var request = new RequestEntity<>(facsimileDTO, HttpMethod.POST, uri);
//        return TEMPLATE.exchange(request, FacsimileDTO.class).getBody();
//    }

    /**
     * Method for saving Facsimile in file-storage
     *
     * @param multipartFile - file for save
     * @return Facsimile as FilePoolDto
     */
//    private FilePoolDto saveAsFile(MultipartFile multipartFile) {
//        try {
//            var file = fileRestTemplateClient.saveFile(multipartFile.getBytes());
//
//            return filePoolService.add(
//                    FilePoolDto.builder()
//                            .storageFileId(file)
//                            .name(multipartFile.getOriginalFilename())
//                            .extension(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
//                            .size((multipartFile.getBytes()).length)
//                            .pageCount(1)
//                            .creator(getCreatorFromSecurity())
//                            .build());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * TODO Remake it with Enum
     * Method for set type FACSIMILE to file.
     *
     * @param multipartFile - facsimile
     * @return facsimile.FACSIMILE File
     */
    private File setTypeFacsimile(MultipartFile multipartFile) throws IOException {
        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        File file = new File(multipartFile.getOriginalFilename().replace(fileExtension, "FACSIMILE"));
        multipartFile.transferTo(file);
        return file;
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
