package com.education.service.notification.impl;

import com.education.entity.Notification;
import com.education.model.dto.NotificationDto;
import com.education.repository.NotificationRepository;
import com.education.service.AbstractService;
import com.education.service.notification.NotificationService;
import com.education.util.Mapper.impl.NotificationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Хафизов Ильмир
 *
 * Представляет реализацию операций над оповещением пользователя
 */
@Service
public class NotificationServiceImpl extends AbstractService<NotificationRepository, Notification, NotificationDto, NotificationMapper> implements NotificationService {
    /**
     * Репозиторий оповещений
     */
    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository repository, NotificationMapper notificationMapper, NotificationRepository notificationRepository, NotificationMapper mapper) {
        super(repository, notificationMapper);
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }


    /**
     * Сохранение оповещений в БД
     *
     * @param notification
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public NotificationDto save(NotificationDto notification) {
        return mapper.toDto(notificationRepository.save(mapper.toEntity(notification)));
    }

    /**
     * Сохранение оповещений в БД
     * @param notificationSet
     */
    @Override
    public void saveAll(List<Notification> notificationSet) {
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
    public List<NotificationDto> findAllById(List<Long> id) {
        List<Notification> notifications = notificationRepository.findAllById(id);
        return mapper.toDto(notifications);
    }
}
