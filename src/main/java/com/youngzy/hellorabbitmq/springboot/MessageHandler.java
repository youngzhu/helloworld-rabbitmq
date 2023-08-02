package com.youngzy.hellorabbitmq.springboot;

import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static org.springframework.amqp.support.AmqpHeaders.DELIVERY_TAG;

/**
 * @author youngzy
 * @since 2023-08-02
 */
@Component
public class MessageHandler {
    @SneakyThrows
    @RabbitListener(queues = "hello.queue.spring")
    public void handleMessage(String body, Channel channel, @Header(DELIVERY_TAG) long deliveryTag) {
        System.out.println("sprintboot handleMessage: " + body);

        channel.basicAck(deliveryTag, false);
    }
}
