package com.education.utils.fileConvertion;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileConversionService {
    Map<String, Object> convertFile(MultipartFile multipartFile);
    Map<String, Object> convertFacsimile(byte [] facsimile);
}
