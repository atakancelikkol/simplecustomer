package com.mycompany.rbmq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Send {
    private final static String QUEUE_NAME = "asdfades";
    public static void main(String[] args) throws Exception {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://jytfwjau:H9cfcclhRBq8C4eIFPWQ0X7ERbDAeisQ@whale.rmq.cloudamqp.com/jytfwjau";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);

        //Recommended settings
        factory.setConnectionTimeout(30000);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queue = "hello";     //queue name
        boolean durable = false;    //durable - RabbitMQ will never lose the queue if a crash occurs
        boolean exclusive = false;  //exclusive - if queue only will be used by one connection
        boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

        channel.queueDeclare(queue, durable, exclusive, autoDelete, null);
        String message = "Hello CloudAMQP!";
        String message2 = "asds";
        String message3 = "asds";
        String exchangeName = "";
        String routingKey = "hello";
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        channel.basicPublish(exchangeName, routingKey, null, message2.getBytes());
        channel.basicPublish(exchangeName, routingKey, null, message3.getBytes());

        System.out.println(" [x] Sent '" + message + "'");


        //DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        //    String message = new String(delivery.getBody(), "UTF-8");
        //    System.out.println(" [x] Received '" + message + "'");
        //};
        //channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}
