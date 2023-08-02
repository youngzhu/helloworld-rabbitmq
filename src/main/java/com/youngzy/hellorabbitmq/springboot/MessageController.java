package com.youngzy.hellorabbitmq.springboot;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author youngzy
 * @since 2023-08-02
 */
@RestController
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send/{name}")
    public String send(@PathVariable(value = "name") String name) {
        // 不可达
//        rabbitTemplate.convertAndSend(name + "，您好！");
//        rabbitTemplate.convertAndSend("hello.key.spring", name + "，您好！");
        // 必须同时指定 exchange 和 routingKey
        rabbitTemplate.convertAndSend("hello.exchange.spring", "hello.key.spring", name + "，您好！");
        return "ok" + new Date();
    }
}
