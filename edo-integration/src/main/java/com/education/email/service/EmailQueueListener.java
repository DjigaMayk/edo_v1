package com.education.email.service;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.EmployeeDto;
import com.education.model.records.ResolutionDtoAndAppealRecord;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class EmailQueueListener {

    private final EmailService emailService;
    private final EurekaClient EUREKA_CLIENT;
    private final String BASE_URL = "/api/rest/appeal";
    private final String SERVICE_NAME = "edo-rest";

    @RabbitListener(queues = RabbitConstant.ADDRESS_CREATE_EMAIL_QUEUE)
    public void createEmail(Long id) {
        sendNotificationOnAppeal(id);
        log.log(Level.INFO, "Отправлено письмо");
    }

    @RabbitListener(queues = RabbitConstant.RESOLUTION_NOTIFICATION_QUEUE)
    public void resolutionNotificationsListener(ResolutionDtoAndAppealRecord resolutionNotificationInfoRecord) {
        log.info("appeal id: " + resolutionNotificationInfoRecord.appealDto().getId());
        log.info("signer FIO: " + resolutionNotificationInfoRecord.resolutionDto().getSigner().getFioNominative());
        var appealDto = resolutionNotificationInfoRecord.appealDto();
        var resolutionDto = resolutionNotificationInfoRecord.resolutionDto();

        try {
            var notificationMailTemplate = "Добрый день, %1$s!\n" +
                    "Вы являетесь %2$s резолюции в Обращении  с номером "
                    + appealDto.getNumber() + "\n" + getURIByInstance(getInstance(),
                    "/byId/" + appealDto.getId()).toURL();
            sendResolutionNotificationToContributor(resolutionDto.getSigner(),
                    notificationMailTemplate, "Подписантом");
            sendResolutionNotificationToContributor(resolutionDto.getCurator(),
                    notificationMailTemplate, "Куратором");
            for (EmployeeDto executor : resolutionDto.getExecutors()) {
                sendResolutionNotificationToContributor(executor,
                        notificationMailTemplate, "Исполнителем");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        log.info("All notifications was sent");
    }

    /**
     * The resolution notification message factory method.
     */
    private void sendResolutionNotificationToContributor(EmployeeDto memberDto,
                                                         String notificationMailTemplate, String role) {
        emailService.sendSimpleEmail(memberDto.getWorkEmail(), "Уведомление о создании резолюции",
                notificationMailTemplate.formatted(memberDto.getFioNominative(), role));
    }

    /**
     * Получает инстанс edo-rest случайным методом
     */
    private InstanceInfo getInstance() {
        List<InstanceInfo> instances = EUREKA_CLIENT.getApplication(SERVICE_NAME).getInstances();
        return instances.get((int) (instances.size() * Math.random()));
    }

    /**
     * Возвращает URI по инстансу
     */
    private URI getURIByInstance(InstanceInfo instanceInfo, String pathVariable) {
        return UriComponentsBuilder.fromPath(BASE_URL + pathVariable)
                .scheme(HttpHost.DEFAULT_SCHEME_NAME)
                .host(instanceInfo.getHostName())
                .port(instanceInfo.getPort())
                .buildAndExpand(pathVariable)
                .toUri();
    }

    /**
     * Метод для рассылки оповещений по почте всем адресантам и подписантам из обращения
     *
     * @param id - для поиска Employee и Appeal в БД
     */
    private void sendNotificationOnAppeal(Long id) {
        try {
            var findAppeal = emailService.findByIdAppeal(id);
            var findEmployee = emailService.findByIdEmployee(id);
            var appealNumber = findAppeal.getNumber();
            var templateMessage = "Добрый день, %1$s!\n%2$s с номером " + appealNumber + "\n" +
                    getURIByInstance(getInstance(), "/byId/" + findEmployee.getId()).toURL();
            assembleAndSendEmail(findAppeal.getAddressee(), templateMessage, "Для вас адресовано Обращение", appealNumber);
            assembleAndSendEmail(findAppeal.getSigner(), templateMessage, "Вы являетесь Подписантом в Обращении", appealNumber);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        log.log(Level.INFO, "Все письма о создании нового обращения отправлены");
    }

    private void assembleAndSendEmail(List<EmployeeDto> employers, String template, String greeting, String id) {
        for (EmployeeDto emp : employers) {
            emailService.sendSimpleEmail(emailService.findByIdEmployee(emp.getId()).getWorkEmail(), "Обращение № " + id,
                    template.formatted(emailService.findByIdEmployee(emp.getId()).getFioNominative(),
                            greeting));
        }
    }

}
