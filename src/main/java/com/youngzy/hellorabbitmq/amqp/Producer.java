package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 生产者
 *
 * @author youngzy
 * @since 2023-07-28
 */
public class Producer {
    /*
    生产者构建示例
     */
    @SneakyThrows
    public static void main(String[] args) {
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
//        Connection conn = factory.newConnection();
        Connection conn = factory.newConnection("HelloWorld");

        // 3. 通过连接创建信道 Channel
        Channel channel = conn.createChannel();

        // 4. 通过信道发送数据
        channel.basicPublish("", "hello.queue.test", null, "Hello RabbitMQ!".getBytes());

        System.out.println("消息发送成功！");
        TimeUnit.SECONDS.sleep(60);

        // 5. 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
