package com.education.service.notification.Feign;


import com.education.model.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign клиент для взаимодействия с микросервисом 'edo-repository' для работы с сущностью 'Notification'.
 * Данный интерфейс предоставляет методы для выполнения различных операций CRUD (Create, Read, Update, Delete)
 * над сущностью 'Notification'.
 */
@FeignClient(name = "edo-repository")
public interface NotificationFeignClient {

    /**
     * Выполняет запрос к микросервису 'edo-repository' для сохранения.
     */
    @PostMapping("/api/repository/notification")
    void save(NotificationDto notificationDto);

    /**
     * Выполняет запрос к микросервису 'edo-repository' для сохранения списка.
     */
    @PostMapping("/api/repository/notification/saveAll")
    void saveAll(List<NotificationDto> notificationSet);

    /**
     * Выполняет запрос к микросервису 'edo-repository' для удаления с указанным идентификатором.
     */
    @DeleteMapping("/api/repository/notification/{id}")
    void delete(@PathVariable("id") Long id);

    /**
     * Выполняет запрос к микросервису 'edo-repository' для поиска по указанному идентификатору.
     */
    @GetMapping("/api/repository/notification/{id}")
    NotificationDto findById(@PathVariable("id") Long id);

    /**
     * Выполняет запрос к микросервису 'edo-repository' для поиска списка по идентификаторам.
     */
    @GetMapping("/api/repository/notification")
    List<NotificationDto> findAllById(@RequestParam("ids") List<Long> id);
}