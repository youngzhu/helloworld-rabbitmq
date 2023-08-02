package com.youngzy.hellorabbitmq.spring.msglistener.container.adapter.json;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@Configuration
public class JsonMessageListenerConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqp://hello:hello@localhost:5672/hello");
        return connectionFactory;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        container.setQueueNames("hello.queue.spring");

        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setMessageConverter(new Jackson2JsonMessageConverter());
        adapter.setDelegate(new JsonMessageHandler());

        container.setMessageListener(adapter);

        return container;
    }
}
