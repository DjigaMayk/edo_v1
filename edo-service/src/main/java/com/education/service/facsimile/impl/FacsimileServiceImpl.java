package com.education.service.facsimile.impl;

import com.education.client.FileRestTemplateClient;
import com.education.feign.feign_facsimile.FacsimileFeignService;
import com.education.model.dto.DepartmentDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.model.enumEntity.EnumFileType;
import com.education.service.facsimile.FacsimileService;
import com.education.service.file_pool.FilePoolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Никита Бадеев
 * <p>
 * Class-service for Facsimile
 */
@Service
@RequiredArgsConstructor
@Log
public class FacsimileServiceImpl implements FacsimileService {
    /**
     * Service and client for saving facsimile in storage
     */
    private final FilePoolService filePoolService;
    private final FileRestTemplateClient fileRestTemplateClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Client for sending and receiving requests
     */
    private final FacsimileFeignService feignClient;


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
    public FacsimileDto save(String jsonFile) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            EmployeeDto employee = objectMapper.treeToValue(jsonNode.get("employee"), EmployeeDto.class);
            DepartmentDto department = objectMapper.treeToValue(jsonNode.get("department"), DepartmentDto.class);
            FilePoolDto filePool = objectMapper.treeToValue(jsonNode.get("file_pool"), FilePoolDto.class);
            var facsimileDTO = FacsimileDto.builder()
                    .employee(employee)
                    .department(department)
                    .file(filePool)
                    .isArchived(false)
                    .build();
            return feignClient.saveFacsimile(facsimileDTO);
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
    public FacsimileDto archiveFacsimile(String jsonFile) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            FacsimileDto facsimileFromJson = objectMapper.treeToValue(jsonNode.get("facsimile"), FacsimileDto.class);
            FacsimileDto facsimileDTO = getById(facsimileFromJson.getId());
            facsimileDTO.setArchived(facsimileFromJson.isArchived());
            filePoolService.moveToArchive(facsimileDTO.getFile().getId());
            return feignClient.archiveFacsimile(facsimileDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for getting facsimile by id
     *
     * @param id Long
     * @return FacsimileDto
     */
    @Override
    public FacsimileDto getById(Long id) {
        return feignClient.getFacsimile(id);
    }


    /**
     * Метод, возвращающий FacsimileDto по заданному employee_id
     *
     * @param id id сущности employee
     * @return объект класса FacsimileDto, соответствующий сущности с указанным id_employee.
     * В случае, если объект с заданным id не найден, метод возвращает null.
     */
    public FacsimileDto getFacsimileByEmployeeId(Long id) {
        try {
            return feignClient.getFacsimileByEmployeeId(id);
        } catch (HttpClientErrorException.NotFound e) {
            log.info("Пользователь с id: " + id + " не найден");
            return null;
        }
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
}
