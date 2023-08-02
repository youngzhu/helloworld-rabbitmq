package com.youngzy.hellorabbitmq.spring.msglistener.rabbitlistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author youngzy
 * @since 2023-08-02
 */
@Component
public class RabbitListenerMessageHandler {
    @RabbitListener(queues = "hello.queue.spring")
    public void xxx(byte[] message) {
        System.out.println("RabbitListener byte[]:" + new String(message));
    }
}
