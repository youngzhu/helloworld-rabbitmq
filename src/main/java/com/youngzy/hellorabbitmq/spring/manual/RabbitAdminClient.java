package com.youngzy.hellorabbitmq.spring.manual;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author youngzy
 * @since 2023-08-01
 */
@ComponentScan
public class RabbitAdminClient {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RabbitAdminClient.class);

        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);

        // 利用 rabbitAdmin 手动声明 交换机、队列、绑定
        {
            // new 的方式
            rabbitAdmin.declareExchange(new DirectExchange("hello.exchange.spring", true, false));
            rabbitAdmin.declareQueue(new Queue("hello.queue.spring", true));
            rabbitAdmin.declareBinding(new Binding("hello.queue.spring", Binding.DestinationType.QUEUE,
                    "hello.exchange.spring", "hello.key.spring", null));
        }

        {
            // build 的方式
            FanoutExchange exchange = ExchangeBuilder.fanoutExchange("hello.exchange.build")
                    .durable(true)
                    .build();
            rabbitAdmin.declareExchange(exchange);

            Queue queue = QueueBuilder.durable("hello.queue.build").build();
            rabbitAdmin.declareQueue(queue);

            Binding binding = BindingBuilder.bind(queue).to(exchange);
            rabbitAdmin.declareBinding(binding);
        }

        context.close();
    }
}
