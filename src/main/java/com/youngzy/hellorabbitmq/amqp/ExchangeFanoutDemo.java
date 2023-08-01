package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 * fanout 是广播的形式，跟 routing key 无关
 * 消息会发送到“所有”与交换机绑定的信道上
 *
 * @author youngzy
 * @since 2023-07-31
 */
public class ExchangeFanoutDemo {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();

        Channel channel = conn.createChannel();

        // 通过信道发送数据
//        channel.basicPublish("hello.exchange.fanout", "", null, "Hello fanout".getBytes());
        channel.basicPublish("hello.exchange.fanout", "xx.xx", null, "Hello fanout xx.xx".getBytes());

        System.out.println("消息发送成功！");

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
