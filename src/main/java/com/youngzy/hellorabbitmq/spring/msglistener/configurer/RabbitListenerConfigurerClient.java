package com.youngzy.hellorabbitmq.spring.msglistener.configurer;

import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * @author youngzy
 * @since 2023-08-01
 */
// enable 这个注解，放两个类上都行
@EnableRabbit
@ComponentScan
public class
RabbitListenerConfigurerClient {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitListenerConfigurerClient.class);

        TimeUnit.SECONDS.sleep(10);

        context.close();
    }
}
