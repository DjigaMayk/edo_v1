package com.education.controller;

import com.education.model.dto.FilePoolDto;
import com.education.service.facsimile.FacsimileService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/service/facsimile")
@AllArgsConstructor
@Log
@ApiModel("Контроллер эдо-сервиса для сущности Facsimile")
public class FacsimileController {

    @ApiModelProperty("Сервис для контроллера")
    private FacsimileService facsimileService;

    @ApiOperation("Сохранить факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "File successfully added."),
            @ApiResponse(code = 404, message = "Not found - The file was not found")
    })
    @PostMapping()
    public ResponseEntity<FilePoolDto> saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFile) {

        log.info("Request for saving Facsimile");
        log.info(multipartFile.getOriginalFilename() + "||" + multipartFile.getName()); //TODO Delete
        if (!(facsimileService.isValidate(multipartFile))) {
            return new ResponseEntity(
                    "Facsimile should be jpg or png and should less than 100x100px", HttpStatus.BAD_REQUEST);
        }
        FilePoolDto filePoolDto = facsimileService.saveAsFile(multipartFile); //TODO Не отдает File Type
        System.out.println(filePoolDto.getFileType());
        return ResponseEntity.ok().body(filePoolDto);
    }
}
