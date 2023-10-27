package com.education.service.file_pool.impl;

import com.education.model.dto.FilePoolDto;
import com.education.service.file_pool.FilePoolService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log
public class FilePoolServiceImpl implements FilePoolService {
    @NotNull
    private final RestTemplate restTemplate;

    @NotNull
    private final EurekaClient eurekaClient;

    private static final String BASE_URL = "/api/repository/file_pool/";
    private static final String URI_SCHEME = "http";

    private static final String SERVICE_NAME = "edo-repository";


    /**
     * findById in db
     *
     * @param id Long
     * @return FilePoolDto
     */
    public FilePoolDto findById(Long id) {
        String lastPathName = "/" + id;
        URI uri = generateUri(this.getInstance(), lastPathName);
        RequestEntity<Object> request = new RequestEntity<>(null, HttpMethod.GET, uri);
        return restTemplate.exchange(request, FilePoolDto.class).getBody();
    }

    /**
     * Add in db method
     *
     * @param filePoolDto FilePoolDto
     * @return FilePoolDto
     */
    public FilePoolDto add(FilePoolDto filePoolDto) {
        URI uri = generateUri(this.getInstance(), "/");
        RequestEntity<Object> request = new RequestEntity(filePoolDto, HttpMethod.POST, uri);
        var uuid =  restTemplate.exchange(request, String.class)
                .getBody();
log.info(uuid);
return new FilePoolDto();
    }

    /**
     * findAllById in db
     *
     * @param ids List<Long>
     * @return List<FilePoolDto>
     */
    public List<FilePoolDto> findAllById(List<Long> ids) {
        URI uri = generateUri(this.getInstance(), "/findAll");
        var request = new RequestEntity<>(ids, HttpMethod.POST, uri);
        var listOfFilePoolDtoType = new ParameterizedTypeReference<List<FilePoolDto>>() {
        };

        return restTemplate.exchange(request, listOfFilePoolDtoType)
                .getBody();
    }

    /**
     * Move to archive in db
     *
     * @param id Long
     */
    public void moveToArchive(Long id) {
        String lastPathName = "/" + id;
        URI uri = generateUri(this.getInstance(), lastPathName);
        var request = new RequestEntity<>(HttpMethod.PUT, uri);
        restTemplate.exchange(request, Void.class);
    }


    /**
     * Предоставляет не заархивированное FilePoolDto номенклатуры из БД по id
     *
     * @param id Long
     * @return FilePoolDto
     */
    public FilePoolDto findByIdNotArchived(Long id) {
        String lastPathName = "/notArchived/" + id;
        URI uri = generateUri(this.getInstance(), lastPathName);
        var request = new RequestEntity<>(null, HttpMethod.GET, uri);
        return restTemplate.exchange(request, FilePoolDto.class)
                .getBody();
    }

    /**
     * Предоставляет список не заархивированных FilePoolDto номенклатур из БД по id
     *
     * @param list List of id
     * @return List of FilePoolDto
     */
    public List<FilePoolDto> findAllByIdNotArchived(Iterable<Long> list) {
        String lastPathName = "/notArchived";
        URI uri = generateUri(this.getInstance(), lastPathName);
        var request = new RequestEntity<>(list, HttpMethod.POST, uri);
        var listOfFilePoolDtoType = new ParameterizedTypeReference<List<FilePoolDto>>() {
        };
        return restTemplate.exchange(request, listOfFilePoolDtoType)
                .getBody();
    }

    /**
     * getInstance
     *
     * @return InstanceInfo
     */
    private InstanceInfo getInstance() {
        Application app = eurekaClient.getApplication(SERVICE_NAME);
        if (app == null) {
            log.warning("EurekaClient  не смогла достучаться до edo-repository");
        }
        List<InstanceInfo> instanceInfos = app.getInstances();
        return instanceInfos.get(new Random().nextInt(instanceInfos.size()));
    }

    /**
     * generateUri
     *
     * @param instance InstanceInfo
     * @param path     String
     * @return URI
     */
    private URI generateUri(InstanceInfo instance, String path) {
        return UriComponentsBuilder.fromPath(BASE_URL + path)
                .scheme(URI_SCHEME)
                .host(instance.getHostName())
                .port(instance.getPort())
                .build()
                .toUri();
    }


}
