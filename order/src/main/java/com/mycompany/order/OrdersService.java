package com.mycompany.order;
import com.mycompany.order.OrderPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

@Slf4j
@Service
@AllArgsConstructor
public class OrdersService {

    private final RestTemplate restTemplate;

    private final OrdersRepository ordersRepository;

    public void registerOrders(OrdersRequest ordersRequest) {
        log.info("products: {}", ordersRequest.products());
        log.info("productsNumber: {}", ordersRequest.productsNumber());

        //todo check if email valid
        //todo check if email not taken
        //todo store customer in db     OK
        log.info("before");
        Boolean isProductAvailable = restTemplate.getForObject(
                "http://localhost:8083/api/v1/products/{products}/{productsNumber}",
                Boolean.class, ordersRequest.products(),ordersRequest.productsNumber()

        );
        Orders orders = Orders.builder()
                .customerId(ordersRequest.customerId())
                .paymentMethod(ordersRequest.paymentMethod())
                .products(ordersRequest.products())
                .productsNumber(ordersRequest.productsNumber())
                .isOrderSuccessful(isProductAvailable)
                .build();
        log.info("isProductAvailable: {}", isProductAvailable);
        String message;
        if (isProductAvailable) {
            message = "Order is Succesful";
        }
        else {
            message = "Order is Failed";
        }
        ordersRepository.save(orders);
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://jytfwjau:H9cfcclhRBq8C4eIFPWQ0X7ERbDAeisQ@whale.rmq.cloudamqp.com/jytfwjau";

        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri(uri);
        } catch (Exception e){

        }
        //Recommended settings
        factory.setConnectionTimeout(30000);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String queue = "hello";     //queue name
            boolean durable = false;    //durable - RabbitMQ will never lose the queue if a crash occurs
            boolean exclusive = false;  //exclusive - if queue only will be used by one connection
            boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes
            channel.queueDeclare(queue, durable, exclusive, autoDelete, null);

            String exchangeName = "";
            String routingKey = "hello";

            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        } catch (Exception e){

        }


    }
}
