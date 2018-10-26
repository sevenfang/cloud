package com.shianxian.trace.flow.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloReceive {

    public void process1(String str) {
        System.out.println("message:"+str);
    }

    @RabbitListener(queues="topic.trace-messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("messages:" + str);
    }

}