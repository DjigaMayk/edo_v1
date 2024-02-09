package com.education.service.facsimile;

import com.education.model.dto.FacsimileDto;
import com.education.model.dto.FilePoolDto;
import com.education.service.BaseInterface;
import org.springframework.web.multipart.MultipartFile;

public interface FacsimileService extends BaseInterface<FacsimileDto> {

    FacsimileDto saveFacsimileEntity(String jsonFile);

    FacsimileDto archiveFacsimile(String jsonFile);

    FilePoolDto saveFacsimile(MultipartFile multipartFile);
}
