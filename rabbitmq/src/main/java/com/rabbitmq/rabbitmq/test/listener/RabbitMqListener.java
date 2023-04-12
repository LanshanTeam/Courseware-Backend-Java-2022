package com.rabbitmq.rabbitmq.test.listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.rabbitmq.test.config.RabbitMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/3/19
 * @Email: 2825097536@qq.com
 */
@Component
public class RabbitMqListener {
    /**
     * 监听直接交换机
     *
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitMqConfig.DIRECT_QUEUE_NAME),
            exchange = @Exchange(value = RabbitMqConfig.DIRECT_EXCHANGE_NAME, type = "direct")
    ))
    public void getMessage(Message message, Channel channel) {
        //接受序列化对象
        Object o = SerializationUtils.deserialize(message.getBody());
        //手动确认,channel.basicAck();
        System.out.println(o);
    }

    /**
     * 监听topic交换机
     *
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitMqConfig.TOPIC_QUEUE_NAME),
            exchange = @Exchange(value = RabbitMqConfig.TOPIC_EXCHANGE_NAME, type = "topic"),
            key = {"binding.#"}
    ))
    public void getMessage2(Message message, Channel channel) {
        //接受序列化对象

        Object o = SerializationUtils.deserialize(message.getBody());
        //手动确认，channel.basicAck();
        System.out.println(o);
    }
}