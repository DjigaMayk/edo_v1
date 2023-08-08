package com.education.client;


import com.education.model.dto.FacsimileDto;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Ярослав Рябоконь
 * Класс для отправки запросов в edo-repositotry
 */
@Component
@RequiredArgsConstructor
public class FacsimileRestTemplateClient {

    /**
     * Объект класса EurekaClient
     */
    private final EurekaClient eurekaClient;
    /**
     * Объект класса RestTemplate
     */
    private final RestTemplate restTemplate;
    /**
     * Базовый URL для API edo-service
     */
    private final String BASIC_URL = "api/repository/facsimile";

    /**
     * Метод, возвращающий FacsimileDto по заданному id
     *
     * @param id id сущности
     * @return объект класса FacsimileDto, соответствующий сущности с указанным id.
     * В случае, если объект с заданным id не найден, метод возвращает null.
     */
    public FacsimileDto getFacsimileById(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            var entity = RequestEntity.get(getDefaultUriComponentBuilder(BASIC_URL + "/{id}")
                    .buildAndExpand(id)
                    .toUri()).headers(headers).build();
            return restTemplate.exchange(entity, FacsimileDto.class).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
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
            HttpHeaders headers = new HttpHeaders();
            var entity = RequestEntity.get(getDefaultUriComponentBuilder(BASIC_URL + "/by-employee" + "/{id}")
                    .buildAndExpand(id)
                    .toUri()).headers(headers).build();
            return restTemplate.exchange(entity, FacsimileDto.class).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    /**
     * Метод, возвращающий случайный экземпляр сервиса edo-repository
     *
     * @return Случайный экземпляр сервиса edo-repository
     */
    private InstanceInfo getRandomInstance() {
        var instances = eurekaClient
                .getApplication("edo-repository")
                .getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Метод, возвращающий общий для всех методов класса объект UriComponentBuilder
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

