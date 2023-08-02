package com.youngzy.hellorabbitmq.spring.msglistener.adapter;

/**
 * @author youngzy
 * @since 2023-08-02
 */
public class MessageHandler {
    public void handleMessage(byte[] message) {
        System.out.println("byte[] msg: " + new String(message));
    }

    // 可以设置消息的 content_type=text 来调用这个方法
    public void handleMessage(String message) {
        System.out.println("String msg: " + message);
    }

    public void processMessage(byte[] message) {
        System.out.println("process byte[] msg: " + new String(message));
    }

    // 可以设置消息的 content_type=text 来调用这个方法
    public void processMessage(String message) {
        System.out.println("process String msg: " + message);
    }
}
