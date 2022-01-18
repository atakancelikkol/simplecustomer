package com.mycompany.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload) {
        log.info("Publishing to {} using routingKey {}. Payload: {}",payload);
        amqpTemplate.convertAndSend(payload);
        log.info("Published to {} using routingKey {}. Payload: {}",payload);
    }

}
