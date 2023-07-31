package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

/**
 * @author youngzy
 * @since 2023-07-31
 */
public class MQUtil {
    @SneakyThrows
    public static Connection getConnection() {
        // 1. 创建连接工厂 ConnectionFactory ，并进行配置
        ConnectionFactory factory = new ConnectionFactory();

//        factory.setHost("localhost");
//        factory.setPort(5672);
//        factory.setUsername("hello");
//        factory.setPassword("hello");
//        factory.setVirtualHost("hello");

        factory.setUri("amqp://hello:hello@localhost:5672/hello");

        factory.setAutomaticRecoveryEnabled(true);
        factory.setNetworkRecoveryInterval(10000);
        factory.setConnectionTimeout(5000);

        // 2. 通过连接工厂创建连接 Connection
        Connection conn = null;
//        Connection conn = factory.newConnection();
        conn = factory.newConnection("HelloWorld");

        return conn;
    }
}
