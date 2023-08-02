package com.youngzy.hellorabbitmq.spring.msglistener.container.adapter.json;

import java.util.Map;

/**
 * @author youngzy
 * @since 2023-08-02
 */
public class JsonMessageHandler {
    public void handleMessage(Map<String, Object> message) {
        System.out.println("Map msg: " + message);
    }
}
