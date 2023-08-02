package com.youngzy.hellorabbitmq.spring.msglistener.configurer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author youngzy
 * @since 2023-08-01
 */
//@EnableRabbit
@Configuration
public class RabbitListenerConfigurerConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqp://hello:hello@localhost:5672/hello");
        return connectionFactory;
    }

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory factory) {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(factory);

        return containerFactory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer() {
        return new RabbitListenerConfigurer() {
            @Override
            public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
                {
                    SimpleRabbitListenerEndpoint endpointDirect = new SimpleRabbitListenerEndpoint();
                    endpointDirect.setId(UUID.randomUUID().toString());
                    endpointDirect.setQueueNames("hello.queue.spring");
                    endpointDirect.setMessageListener(new ChannelAwareMessageListener() {
                        @Override
                        public void onMessage(Message message, Channel channel) throws Exception {
                            System.out.println("endpointDirect:" + new String(message.getBody()));
                        }
                    });
                    registrar.registerEndpoint(endpointDirect);
                }

                {
                    SimpleRabbitListenerEndpoint endpointFanout = new SimpleRabbitListenerEndpoint();
                    endpointFanout.setId(UUID.randomUUID().toString());
                    endpointFanout.setQueueNames("hello.queue.fanout1");
                    endpointFanout.setMessageListener(new ChannelAwareMessageListener() {
                        @Override
                        public void onMessage(Message message, Channel channel) throws Exception {
                            System.out.println("endpointFanout:" + new String(message.getBody()));
                        }
                    });
                    registrar.registerEndpoint(endpointFanout);
                }
            }
        };
    }
}
