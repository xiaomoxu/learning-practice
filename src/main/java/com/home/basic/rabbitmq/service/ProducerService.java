package com.home.basic.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToMessageQueue(String msg) {
        rabbitTemplate.convertAndSend("topicExchange", "com.bassoon.queue.stock", msg);
    }
}
