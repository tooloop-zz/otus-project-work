package ru.otus.asamofalov.pw.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.asamofalov.pw.Constants;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//@Configuration
//public class RabbitMqConfig {

//    @Bean
//    public ConnectionFactory clientConnectionFactory(){
//        return new ConnectionFactory();
//    }
//
//    @Bean
//    public Connection clientConnection(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
//        connectionFactory.setHost("localhost");
//        return connectionFactory.newConnection();
//    }
//
//    @Bean
//    public Channel clientChannel(Connection connection) throws IOException {
//        Channel channel =connection.createChannel();
//        channel.queueDeclare(Constants.SERVICE_PASSPORT_REQUEST_QUEUE, false, false, false, null);
//        channel.queueDeclare(Constants.SERVICE_PASSPORT_RESPONSE_QUEUE, false, false, false, null);
//        return channel;
//    }
//}
