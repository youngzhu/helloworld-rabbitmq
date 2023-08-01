package com.youngzy.hellorabbitmq.spring.template;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;

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
        Message message = new Message("hello template".getBytes(), properties);
        template.send("hello.exchange.spring", "hello.key.spring", message);

        Person person = new Person("张三", 33);
        template.convertAndSend("hello.exchange.spring", "hello.key.spring", person);

        context.close();
    }

    static class Person implements Serializable {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
