package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 *
 * @author youngzy
 * @since 2023-08-01
 */
public class DeadLetterDemo {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();
        Channel channel = conn.createChannel();

        // 死信交换机
        channel.exchangeDeclare("hello.exchange.dl", BuiltinExchangeType.DIRECT, true, false, null);
        channel.queueDeclare("hello.queue.dl", true, false, false, null);
        channel.queueBind("hello.queue.dl", "hello.exchange.dl", "hello.routingKey.dl");

        // 正常交换机
        channel.exchangeDeclare("hello.exchange.normal", BuiltinExchangeType.DIRECT, true, false, null);
        Map<String, Object> map = new HashMap<>();
        // 超过设定的时间未消费，就会进入死信交换机
        map.put("x-message-ttl", 10000); // 10s
        map.put("x-dead-letter-exchange", "hello.exchange.dl");
        map.put("x-dead-letter-routing-key", "hello.routingKey.dl");
        channel.queueDeclare("hello.queue.normal", true, false, false, map);
        channel.queueBind("hello.queue.normal", "hello.exchange.normal", "hello.routingKey.normal");

        channel.basicPublish("hello.exchange.normal", "hello.routingKey.normal", null, "dead-letter test".getBytes());

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
