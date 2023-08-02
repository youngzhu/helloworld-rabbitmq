package com.youngzy.hellorabbitmq.spring.msglistener.rabbitlistener;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@EnableRabbit
@ComponentScan
public class RabbitListenerClient {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitListenerClient.class);

        TimeUnit.SECONDS.sleep(10);

        context.close();
    }
}
