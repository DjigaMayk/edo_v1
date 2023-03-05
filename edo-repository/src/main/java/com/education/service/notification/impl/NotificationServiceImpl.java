package com.education.service.notification.impl;

import com.education.entity.Notification;
import com.education.model.dto.NotificationDto;
import com.education.repository.NotificationRepository;
import com.education.service.notification.NotificationService;
import com.education.util.Mapper.impl.NotificationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Хафизов Ильмир
 *
 * Представляет реализацию операций над оповещением пользователя
 */
@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    /**
     * Репозиторий оповещений
     */
    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    /**
     * Сохранение оповещений в БД
     *
     * @param notification
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(NotificationDto notification) {
        notificationRepository.saveAndFlush(mapper.toEntity(notification));
    }

    /**
     * Сохранение оповещений в БД
     * @param notificationSet
     */
    @Override
    public void saveAll(Set<Notification> notificationSet) {
        notificationRepository.saveAllAndFlush(notificationSet);
    }

    /**
     * Удаление оповещений в БД по id
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    /**
     * Предоставляет NotificationDto оповещений из БД по id
     * @param id
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public NotificationDto findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        return notification != null ? mapper.toDto(notification) : null;
    }

    /**
     * Предоставляет список Notification оповещений из БД по id
     * @param id
     * @return
     */
    @Override
    public Set<NotificationDto> findAllById(List<Long> id) {
        Set<Notification> notifications = notificationRepository.findAllById(id)
                .stream()
                .collect(Collectors.toSet());
        return mapper.toDto(notifications);
    }
}
