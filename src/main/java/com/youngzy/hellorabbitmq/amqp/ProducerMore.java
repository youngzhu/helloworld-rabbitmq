package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 * 生产者（更多的配置）
 *
 * @author youngzy
 * @since 2023-07-28
 */
public class ProducerMore {
    /*
    生产者构建示例
     */
    @SneakyThrows
    public static void main(String[] args) {
        Connection conn = getConnection();

        Channel channel = conn.createChannel();

        // 开启 confirm
        channel.confirmSelect();

        Map<String, Object> headers = new HashMap<>();
        headers.put("msgId", UUID.randomUUID().toString());

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .contentEncoding("utf-8")
                .contentType("text/plain")
                .deliveryMode(2)
                .headers(headers)
                .build();
//        channel.basicPublish("hello.exchange.direct", "hello.routingKey.direct",
//                properties, "你好 RabbitMQ!".getBytes());
        // 测试 return 监听，mandatory 必须为 true
        channel.basicPublish("hello.exchange.direct", "hello.routingKey.none", true,
                properties, "再见 RabbitMQ!".getBytes());

        // 添加 confirm 监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("消息成功到达交换机！");
                System.out.println("l: " + l);
                System.out.println("b: " + b);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息未能到达交换机！！！");
                System.out.println("l: " + l);
                System.out.println("b: " + b);
            }
        });

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                // 交换机不存在 会直接报错
                System.out.println("消息不可达，可能是没找到相应队列");
                System.out.println("i: " + i);
                System.out.println("s: " + s);
                System.out.println("s1: " + s1);
                System.out.println("s2: " + s2);
                System.out.println("basicProperties: " + basicProperties);
                System.out.println("body: " + new String(bytes));

            }
        });

        TimeUnit.SECONDS.sleep(60);

        System.out.println("消息发送成功！");

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
