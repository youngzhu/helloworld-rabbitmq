package com.youngzy.hellorabbitmq.spring.template.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@ComponentScan
public class Client {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Client.class);

        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.setRoutingKey("hello.key.spring");

        MessageProperties properties = new MessageProperties();
        properties.setContentEncoding("utf-8");
        properties.setContentType("text/plain");
        Message message = new Message("ConfirmCallback".getBytes(), properties);
//        template.send("hello.exchange.spring", "hello.key.spring", message);
        // 正确的 exchange + 错误的 routingKey ，才会触发 ReturnsCallback
        template.send("hello.exchange.spring", "hello.key.spring1", message);

        context.close();
    }

}
