package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 *
 * @author youngzy
 * @since 2023-07-28
 */
public class Consumer {
    /*
    消费者构建示例
     */
    @SneakyThrows
    public static void main(String[] args) {
        // 1. 创建连接工厂 ConnectionFactory ，并进行配置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("hello");
        factory.setPassword("hello");
        factory.setVirtualHost("hello");

        // 2. 通过连接工厂创建连接 Connection
        Connection conn = factory.newConnection();

        // 3. 通过连接创建信道 Channel
        Channel channel = conn.createChannel();

        // 4. 声明队列 Queue （默认交换机）【交换机声明、关系绑定】
        channel.queueDeclare("hello.queue.test", true, false, false, null);

        // 5. 创建消费者，重写 handleDelivery 方法处理接收的消息
        channel.basicConsume("hello.queue.test", new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope: " + envelope);
                System.out.println("properties: " + properties);
                System.out.println("body: " + new String(body));
            }
        });

        TimeUnit.SECONDS.sleep(120);

        // 6. 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
