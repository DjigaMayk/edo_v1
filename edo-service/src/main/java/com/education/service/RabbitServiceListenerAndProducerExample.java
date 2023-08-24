package com.education.service;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class RabbitServiceListenerAndProducerExample {

    private AmqpTemplate template;

    @RabbitListener(queues = RabbitConstant.ADDRESS_CREATE_SERVICE)
    public void getMessage(AddressDto addressDto) {

        template.convertAndSend(RabbitConstant.EXCHANGE, RabbitConstant.ADDRESS_CREATE_DB_QUEUE, addressDto);
        log.log(Level.INFO, "Сущность принята и отправлена в DB " + addressDto.toString());


    }

}
