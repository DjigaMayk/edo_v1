package com.education.email.configuration;

import com.education.model.constant.RabbitConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue newEmailQueue() {
        return new Queue(RabbitConstant.addressCreateEmailQueue, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitConstant.exchange);
    }

    @Bean
    public Binding binding(Queue newEmailQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(newEmailQueue)
                .to(exchange)
                .with(RabbitConstant.addressCreateEmailQueue);
    }

    @Bean
    public Queue resolutionNotificationQueue() {
        return new Queue(RabbitConstant.resolutionNotificationQueue, false);
    }

    @Bean
    public Binding resolutionNotificationBinding(Queue resolutionNotificationQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(resolutionNotificationQueue)
                .to(exchange)
                .with(RabbitConstant.resolutionNotificationQueue);
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
                                                           SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                           ObjectMapper objectMapper) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter(objectMapper));
        return factory;
    }
}
