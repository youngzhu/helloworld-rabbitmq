package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 * 消费者（更多的设置）
 *
 * @author youngzy
 * @since 2023-07-28
 */
public class ConsumerMore {
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();
        Channel channel = conn.createChannel();

        // 限流
        // 0 表示不限制大小
        // 1 表示每1条返回一个确认信息 ack
        // false 表示 非全局
        channel.basicQos(0, 1, false);
        channel.basicConsume("hello.queue.test", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("envelope: " + envelope);
                System.out.println("properties: " + properties);
                System.out.println("body: " + new String(body));

                // ack
                long deliveryTag = envelope.getDeliveryTag();
                // 消费成功
//                channel.basicAck(deliveryTag, true);
                // 消费失败
                // 两者的区别：是否支持批量
                channel.basicNack(deliveryTag, false, true);
                channel.basicReject(deliveryTag, true);
            }
        });

        TimeUnit.SECONDS.sleep(120);

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
