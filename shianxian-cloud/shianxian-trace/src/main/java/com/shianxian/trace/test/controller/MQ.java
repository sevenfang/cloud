package com.shianxian.trace.test.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 赵明明
 * @Date: 2018/10/12 9:30
 * @Description:
 */
@RestController
public class MQ {

    @Autowired
    private AmqpTemplate amqpTemplate;



    @GetMapping("send")
    private Object send() {
        amqpTemplate.convertAndSend("queue","hello,rabbit~");
        return 1;
    }

}

