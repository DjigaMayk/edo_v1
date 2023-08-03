package com.education.client;

import com.education.model.dto.FilePoolDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * @author Дарья Лукьянова
 * Класс для отправки запросов в edo-service
 */
@Component
@RequiredArgsConstructor
public class FileRestTemplateClient {
    /**
     * Объект класса EurekaClient
     */
    private final EurekaClient eurekaClient;
    /**
     * Объект класса RestTemplate
     */
    private final RestTemplate restTemplate;
    /**
     * Базовый URL для API edo-rest
     */
    private final String BASIC_URL = "api/service/file";
    /**
     * Метод, отправляющий файл в edo-service
     *
     * @param multipartFile файл, который мы отправляем.
     */
    public FilePoolDto uploadFile(MultipartFile multipartFile) {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", multipartFile.getResource());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return restTemplate.exchange(getDefaultUriComponentBuilder(BASIC_URL)
                .build()
                .toUri(), HttpMethod.POST, requestEntity, FilePoolDto.class).getBody();
    }

    /**
     * Метод, получающий файл из edo-service и сохраняющий его на локальный диск
     *
     * @param uuid файл, который мы отправляем.
     */
    public void loadFile(UUID uuid) {
        ResponseEntity<byte []> response = restTemplate.getForEntity(getDefaultUriComponentBuilder(BASIC_URL + "/" + uuid)
                .build()
                .toUri(), byte[].class);
        try {
            Files.write(Paths.get("MYPDF.pdf"), response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, возвращающий случайный экземпляр сервиса edo-service
     *
     * @return Случайный экземпляр сервиса edo-service
     */
    private InstanceInfo getRandomInstance() {
        var instances = eurekaClient
                .getApplication("edo-service")
                .getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Метод, возвращающий общий для всех методов класса объект UriComponentsBuilder
     *
     * @param path адрес api
     * @return объект UriComponentsBuilder
     */
    private UriComponentsBuilder getDefaultUriComponentBuilder(String path) {
        InstanceInfo instanceInfo = getRandomInstance();
        return UriComponentsBuilder
                .fromPath(path)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort());
    }
}
