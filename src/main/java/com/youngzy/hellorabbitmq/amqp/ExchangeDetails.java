package com.youngzy.hellorabbitmq.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.youngzy.hellorabbitmq.amqp.MQUtil.getConnection;

/**
 * Test没法持久化，控制台看不到效果！！！
 *
 * @author youngzy
 * @since 2023-07-31
 */
public class ExchangeDetails {
    @SneakyThrows
    @Test
    public void testDirect() {
        Connection conn = getConnection();

        Channel channel = conn.createChannel();

        // 通过信道发送数据
        channel.basicPublish("hello.exchange.direct", "hello.queue.direct", null, "Hello direct".getBytes());

        System.out.println("消息发送成功！");
//        TimeUnit.SECONDS.sleep(60);

        // 关闭 Channel 和 Connection
        channel.close();
        conn.close();
    }
}
