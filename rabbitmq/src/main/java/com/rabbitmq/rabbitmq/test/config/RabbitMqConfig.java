package com.rabbitmq.rabbitmq.test.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


/**
 * @description:
 * @author: feiWoSCun
 * @Time: 2023/3/19
 * @Email: 2825097536@qq.com
 */

@Configuration
public class RabbitMqConfig {
    public static final String DIRECT_EXCHANGE_NAME = "luobin_exchange";
    public static final String DIRECT_QUEUE_NAME = "luobin_direct";
    public static final String TOPIC_EXCHANGE_NAME = "luobin_topic_exchange";
    public static final String TOPIC_QUEUE_NAME = "luobin_topic";

    /**
     * 直接交换机
     */
    @Bean("directExchange")
    public Exchange getExchange() {
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).build();
    }

    /**
     * 主题交换机
     *
     * @return
     */
    @Bean("topicExchange")
    public Exchange getTopicExchange() {

        return ExchangeBuilder.topicExchange(TOPIC_EXCHANGE_NAME).build();
    }

    /**
     * direct队列
     *
     * @return
     */
    @Bean("directQueue")
    public Queue getQueue() {
        return QueueBuilder.durable(DIRECT_QUEUE_NAME).build();
    }

    /**
     * topic队列
     *
     * @return
     */
    @Bean("topicQueue")
    public Queue topicQueue() {
        return QueueBuilder.durable(TOPIC_QUEUE_NAME).build();
    }

    /**
     * binding 直接
     */

    @Bean
    public Binding bindingDir(@Qualifier("directQueue") Queue queue, @Qualifier("directExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("binding_testDir").noargs();
    }

    @Bean
    public Binding bindingTop(@Qualifier("topicQueue") Queue queue, @Qualifier("topicExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("binding.testTop").noargs();
    }

    /**
     * 主题交换机
     *
     * @param queue
     * @param exchange
     * @return
     */


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 设置消息确认
     *
     * @throws Exception
     */
    @PostConstruct
    public void after() throws Exception {
        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            if (b) {
                String a = correlationData.getId();
                System.out.println("消息" + a + "确认成功");
            } else {
                System.out.println("消息确认失败");
            }
        });
    }
}