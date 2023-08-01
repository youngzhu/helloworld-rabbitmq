package com.youngzy.hellorabbitmq.spring.auto;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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

        // 自动声明 交换机、队列、绑定
        // 建立了连接才会创建交换机等
        // 仅仅获取 rabbitAdmin 之类的不会创建
        Connection connection = context.getBean(ConnectionFactory.class).createConnection();
        connection.close();

        context.close();
    }
}
