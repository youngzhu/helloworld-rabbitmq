package com.youngzy.hellorabbitmq.spring.msglistener.adapter;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@Configuration
public class MessageListenerConfig {
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
        // 可以指定处理消息的方法
        adapter.setDefaultListenerMethod("processMessage");
        adapter.setDelegate(new MessageHandler());

        container.setMessageListener(adapter);

        return container;
    }
}
