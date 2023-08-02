package com.youngzy.hellorabbitmq.spring.msglistener.adapter.json;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@ComponentScan
public class JsonMessageListenerClient {
    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JsonMessageListenerClient.class);

        TimeUnit.SECONDS.sleep(10);

        context.close();
    }
}
