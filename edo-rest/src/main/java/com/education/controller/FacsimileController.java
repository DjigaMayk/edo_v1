package com.education.controller;

import com.education.model.dto.FacsimileDTO;
import com.education.service.facsimile.FacsimileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO

@Log
@ApiOperation("Facsimile API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest/facsimile")
public class FacsimileController {

    private final FacsimileService facsimileService;

    @ApiOperation(value = "Сохранение Факсимиле")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Facsimile saved"),
            @ApiResponse(code = 404, message = "Facsimile not found")
    })
    @PostMapping
    public ResponseEntity<FacsimileDTO> saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFile) {
        log.info("Request for saving facsimile");
        if (multipartFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.NOT_FOUND);
        }

        String fileExtension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());                         //TODO Mb should be in edo-service
        if (!(fileExtension.equalsIgnoreCase("jpg")
                || fileExtension.equalsIgnoreCase("jpeg")
                || fileExtension.equalsIgnoreCase("png"))) {
            return new ResponseEntity("Wrong extension of file. Should be jpg, JPEG or PNG", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (image.getWidth() > 100 || image.getHeight() > 100) {
                return new ResponseEntity("Too large file! Size should be less than 100x100 px", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().body(facsimileService.saveFacsimile(multipartFile));
    }
}
