package com.youngzy.hellorabbitmq.spring.msglistener;

import lombok.SneakyThrows;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@ComponentScan
public class MessageListenerClient {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MessageListenerClient.class);

        TimeUnit.SECONDS.sleep(10);

        context.close();
    }
}
