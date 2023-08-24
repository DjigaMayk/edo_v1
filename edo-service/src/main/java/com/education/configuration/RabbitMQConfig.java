package com.education.configuration;

import com.education.model.constant.RabbitConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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
public class RabbitMQConfig {
    @Bean
    public Queue addressCreateDB() {
        return new Queue(RabbitConstant.ADDRESS_CREATE_DB_QUEUE, false);
    }

    @Bean
    public Queue addressCreateService() {
        return new Queue(RabbitConstant.ADDRESS_CREATE_SERVICE_QUEUE, false);
    }

    @Bean
    public Queue appealReadService() {
        return new Queue(RabbitConstant.ADDRESS_APPEAL_IS_READ, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(RabbitConstant.EXCHANGE);
    }

    @Bean
    public Binding binding(Queue addressCreateDB, DirectExchange exchange) {
        return BindingBuilder
                .bind(addressCreateDB)
                .to(exchange)
                .with(addressCreateDB.getName());
    }


    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
                                                           SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        ObjectMapper mapper =
                new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat());

        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter(mapper));

        return factory;
    }
}
