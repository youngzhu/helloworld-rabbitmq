package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 * 交换机、队列、绑定 的声明
 *
 * @author youngzy
 * @since 2023-07-31
 */
public class Declare {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();
        Channel channel = conn.createChannel();

        // 声明（创建）交换机
        channel.exchangeDeclare("hello.exchange.direct", BuiltinExchangeType.DIRECT,
                true, false, null);

        // 声明队列 Queue
        channel.queueDeclare("hello.queue.test", true, false, false, null);

        // 声明绑定
        channel.queueBind("hello.queue.test", "hello.exchange.direct", "hello.routingKey.test");
    }
}
