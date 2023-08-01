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
public class DeclareMore {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();
        Channel channel = conn.createChannel();

        {
            /** DIRECT **/
            // 声明（创建）交换机
            channel.exchangeDeclare("hello.exchange.direct", BuiltinExchangeType.DIRECT,
                    true, false, null);
            // 声明队列 Queue
            channel.queueDeclare("hello.queue.direct", true, false, false, null);
            // 声明绑定
            channel.queueBind("hello.queue.direct", "hello.exchange.direct", "hello.routingKey.direct");
        }

        {
            /** FANOUT **/
            // 声明（创建）交换机
            channel.exchangeDeclare("hello.exchange.fanout", BuiltinExchangeType.FANOUT,
                    true, false, null);
            // 声明队列 Queue
            channel.queueDeclare("hello.queue.fanout1", true, false, false, null);
            channel.queueDeclare("hello.queue.fanout2", true, false, false, null);
            // 声明绑定
            channel.queueBind("hello.queue.fanout1", "hello.exchange.fanout", "");
            channel.queueBind("hello.queue.fanout2", "hello.exchange.fanout", "");
        }

        {
            /** TOPIC **/
            // 声明（创建）交换机
            channel.exchangeDeclare("hello.exchange.topic", BuiltinExchangeType.TOPIC,
                    true, false, null);
            // 声明队列 Queue
            channel.queueDeclare("hello.queue.topic1", true, false, false, null);
            channel.queueDeclare("hello.queue.topic2", true, false, false, null);
            // 声明绑定
            channel.queueBind("hello.queue.topic1", "hello.exchange.topic", "hello.routingKey.topic.*");
            channel.queueBind("hello.queue.topic2", "hello.exchange.topic", "hello.routingKey.topic.#");
        }
    }
}
