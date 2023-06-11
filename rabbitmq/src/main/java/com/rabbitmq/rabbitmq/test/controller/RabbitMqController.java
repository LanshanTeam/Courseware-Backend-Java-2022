package com.rabbitmq.rabbitmq.test.controller;

import com.rabbitmq.rabbitmq.test.config.RabbitMqConfig;
import com.rabbitmq.rabbitmq.test.pojo.Animal;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/3/19
 * @Email: 2825097536@qq.com
 */
@RestController
public class RabbitMqController {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public String testMq() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_NAME, "binding.#",
                new Animal("罗彬", 12, 1), new CorrelationData("你好"));
        return "success";
    }

    @RequestMapping("/test2")
    public String testMq2() {

        rabbitTemplate.convertAndSend(RabbitMqConfig.DIRECT_EXCHANGE_NAME, "binding_testDir",
                new Animal("罗彬", 12, 1), new CorrelationData("你好"));
        return "success";
    }
}
