package com.education.controller;

import com.education.model.constant.RabbitConstant;
import com.education.model.dto.AddressDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Пример Producer для отправки данных в очередь
 */
@RestController
@RequestMapping("api/rest/rabbit")
@Log4j2
@Tag(name = "Пример работы с Rabbit")
public class RabbitProducerExample {

    @Autowired
    private AmqpTemplate template;

    @PostMapping
    public ResponseEntity<String> createObject(@RequestBody AddressDto addressDto) {

        template.convertAndSend(RabbitConstant.EXCHANGE, RabbitConstant.ADDRESS_CREATE_SERVICE, addressDto);
        log.log(Level.INFO, "Сущность отправлена " + addressDto.toString());

        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }


}
