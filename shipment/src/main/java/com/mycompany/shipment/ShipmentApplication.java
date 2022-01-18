package com.mycompany.shipment;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = ShipmentController.class)
@SpringBootApplication
public class ShipmentApplication {
    public static void main(String[] args) {

        SpringApplication.run(ShipmentApplication.class, args);
        try {
            String queue = "hello";
            String uri = System.getenv("CLOUDAMQP_URL");
            if (uri == null) uri = "amqps://jytfwjau:H9cfcclhRBq8C4eIFPWQ0X7ERbDAeisQ@whale.rmq.cloudamqp.com/jytfwjau";

            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);

            //Recommended settings
            factory.setConnectionTimeout(30000);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });

        } catch (Exception e){

        }

    }
}
