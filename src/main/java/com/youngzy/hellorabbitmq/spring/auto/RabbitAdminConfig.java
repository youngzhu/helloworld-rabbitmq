package com.youngzy.hellorabbitmq.spring.auto;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@Configuration
public class RabbitAdminConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqp://hello:hello@localhost:5672/hello");
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

    @Bean
    public Exchange topicExchage() {
        return ExchangeBuilder.topicExchange("hello.exchange.auto").build();
    }

    @Bean
    public Queue topicQueue() {
        return QueueBuilder.durable("hello.queue.auto").build();
    }

    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue())
                .to(topicExchage())
                .with("hello.key.auto.#").
                noargs();
    }
}
