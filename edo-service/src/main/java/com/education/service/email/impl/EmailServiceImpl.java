package com.education.service.email.impl;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AppealDto;
import com.education.model.dto.EmployeeDto;
import com.education.model.dto.NotificationDto;
import com.education.model.enumEntity.EnumNotification;
import com.education.service.email.EmailService;
import com.education.service.notification.Feign.NotificationFeignClient;
import com.education.service.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmailServiceImpl implements EmailService {
    private final AmqpTemplate amqpTemplate;

    private final NotificationFeignClient feignClient;

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
        feignClient.save(notificationDto);

        log.log(Level.INFO, "Отправлен запрос в очередь по рассылке оповещений");
    }

}
