package com.education.feign.feign_facsimile;

import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import jakarta.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Катя Трофимова
 * <p>
 * FeignClient for Facsimile
 */
@FeignClient(name = "edo-service", path = "/api/service/facsimile")
public interface FacsimileFeignClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA)
    FilePoolDto saveFacsimile(@RequestPart("facsimile") MultipartFile multipartFile);

    @PostMapping("/")
    FacsimileDto saveFacsimileEntity(@RequestBody String jsonFile);

    @DeleteMapping("/archive")
    FacsimileDto archiveFacsimile(@RequestBody String jsonFile);

}
