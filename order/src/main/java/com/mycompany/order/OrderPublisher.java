package com.mycompany.order;

import com.mycompany.order.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Service
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    //@Bean
    public void OrderSuccessfulPublish(Orders order) {

        System.out.printf("Hello 111111");
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, order);
        System.out.printf("Hello 222222");
    }
}
