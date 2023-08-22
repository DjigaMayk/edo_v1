package com.education.service.email.impl;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.NotificationDto;
import com.education.model.dto.ResolutionDto;
import com.education.model.enumEntity.EnumNotification;
import com.education.model.records.ResolutionDtoAndAppealRecord;
import com.education.service.appeal.AppealService;
import com.education.service.email.EmailService;
import com.education.service.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmailServiceImpl implements EmailService {
    private final AmqpTemplate amqpTemplate;

    private final NotificationService notificationService;

    private final AppealService appealService;

    /**
     * Инициирует рассылку оповещений всем адресантам и подписантам и создает новую сущность Notification
     *
     * @param appealDto - обращение
     */
    @Override
    public void sendNotificationOnAppeal(AppealDto appealDto) {
        amqpTemplate.convertAndSend(RabbitConstant.exchange, RabbitConstant.addressCreateEmailQueue, appealDto);

        var notificationDto = new NotificationDto();
        notificationDto.setEnumNotification(EnumNotification.EMAIL);
        notificationDto.setEmployee(EmployeeDto.builder().id(1L).build());
        notificationService.save(notificationDto);

        log.info("Отправлен запрос в очередь по рассылке оповещений");
    }

    /**
     * После создания резолюции направляет письмо: Подписанту, Исполнителям и Куратору
     * о том, что была создана резолюция, к которой относится пользователь
     *
     * @param resolutionDto - резолюция
     */
    @Override
    public void sendNotificationOnResolution(ResolutionDto resolutionDto) {

        log.info("sendNotificationOnResolution started");

        var resolutionNotificationInfoRecord = new ResolutionDtoAndAppealRecord(resolutionDto,
                appealService.findByQuestion(resolutionDto.getQuestion()));
        amqpTemplate.convertAndSend(RabbitConstant.exchange, RabbitConstant.resolutionNotificationQueue,
                resolutionNotificationInfoRecord);

        log.info("Отправлен запрос в очередь по рассылке " +
                "оповещений при создании резолюции");
        // save the resolution notification for signer to database
        var signerNotificationDto = new NotificationDto();
        signerNotificationDto.setEnumNotification(EnumNotification.EMAIL);
        signerNotificationDto.setEmployee(resolutionDto.getSigner());
        notificationService.save(signerNotificationDto);

        // save the resolution notifications for executors to database
        resolutionDto.getExecutors().forEach(
                executor -> {
                    var executorNotificationDto = new NotificationDto();
                    executorNotificationDto.setEnumNotification(EnumNotification.EMAIL);
                    executorNotificationDto.setEmployee(executor);
                    notificationService.save(executorNotificationDto);
                }
        );

        // save the resolution notification for curator to database
        var curatorNotificationDto = new NotificationDto();
        curatorNotificationDto.setEnumNotification(EnumNotification.EMAIL);
        curatorNotificationDto.setEmployee(resolutionDto.getCurator());
        notificationService.save(curatorNotificationDto);

    }
}
