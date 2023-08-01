package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 *
 * @author youngzy
 * @since 2023-07-31
 */
public class ExchangeTopicDemo {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();

        Channel channel = conn.createChannel();

        // 通过信道发送数据
        channel.basicPublish("hello.exchange.topic", "hello.routingKey.topic", null, "Hello topic".getBytes());
        channel.basicPublish("hello.exchange.topic", "hello.routingKey.topic.", null, "Hello topic.".getBytes());
        channel.basicPublish("hello.exchange.topic", "hello.routingKey.topic.topic1", null, "Hello topic1".getBytes());
        channel.basicPublish("hello.exchange.topic", "hello.routingKey.topic.topic1.sub1", null, "Hello topic1.sub1".getBytes());

        System.out.println("消息发送成功！");

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
