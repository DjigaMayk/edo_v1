package com.education.service.notification;

import com.education.entity.Notification;
import com.education.model.dto.NotificationDto;

import java.util.List;
import java.util.Set;

/**
 * @author Хафизов Ильмир
 *
 * Представляет список операций над оповещением пользователя
 */
public interface NotificationService {

    /**
     * Сохранение оповещения в БД
     *
     * @param notification
     */
    void save(NotificationDto notification);

    /**
     * Сохранение оповещений в БД
     * @param notificationSet
     */
    void saveAll(Set<Notification> notificationSet);

    /**
     * Удаление оповещений в БД по id
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Предоставляет NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    NotificationDto findById(Long id);

    /**
     * Предоставляет список NotificationDto оповещений из БД по id
     *
     * @param id
     * @return
     */
    Set<NotificationDto> findAllById(List<Long> id);
}
