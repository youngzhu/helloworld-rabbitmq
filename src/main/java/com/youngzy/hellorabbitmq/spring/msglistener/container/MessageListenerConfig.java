package com.youngzy.hellorabbitmq.spring.msglistener.container;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.AcknowledgeMode.MANUAL;

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

        {
            // 自动确认回执
            /*container.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    System.out.println("MessageProperties:" + message.getMessageProperties());
                    System.out.println("Body: " + new String(message.getBody()));
                }
            });*/
        }

        {
            // 手动确认回执
            container.setAcknowledgeMode(MANUAL);
            container.setMessageListener(new ChannelAwareMessageListener() {
                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    System.out.println("MessageProperties:" + message.getMessageProperties());
                    System.out.println("Body: " + new String(message.getBody()));

                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                }
            });
        }

        return container;
    }
}
