package com.education.service.notification.impl;

import com.education.model.dto.NotificationDto;
import com.education.service.notification.Feign.NotificationFeignClient;
import com.education.service.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Хафизов Ильмир
 * <p>
 * Представляет реализацию операций над оповещением пользователя
 */
@Service
@AllArgsConstructor
@Log
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFeignClient feignClient;

    /**
     * Сохраняет новое уведомление, используя Feign клиент для отправки запроса к микросервису 'edo-repository'.
     */
    @Override
    public void save(NotificationDto notificationDto) {
        feignClient.save(notificationDto);
    }

    /**
     * Сохраняет список уведомлений, используя Feign клиент для отправки запроса к микросервису 'edo-repository'.
     */
    @Override
    public void saveAll(List<NotificationDto> notificationSet) {
        feignClient.saveAll(notificationSet);
    }

    /**
     * Удаляет уведомление с указанным идентификатором, используя Feign клиент для отправки запроса к микросервису 'edo-repository'.
     */
    @Override
    public void delete(Long id) {
        feignClient.delete(id);
    }

    /**
     * Находит уведомление по указанному идентификатору, используя Feign клиент для отправки запроса к микросервису 'edo-repository'.
     */
    @Override
    public NotificationDto findById(Long id) {
        return feignClient.findById(id);
    }

    /**
     * Находит список уведомлений по списку идентификаторов, используя Feign клиент для отправки запроса к микросервису 'edo-repository'.
     */
    @Override
    public List<NotificationDto> findAllById(List<Long> id) {
        return feignClient.findAllById(id);
    }
}
