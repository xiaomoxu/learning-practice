package com.home.basic.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    @Bean
    public Queue trainingQueue() {
        return new Queue("com.ac.training.queue");
    }


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("com.ac.training.direct.exchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("com.ac.training.topic.exchange");
    }

    @Bean
    public Binding bindingExchangeTopicA(Queue trainingQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(trainingQueue).to(directExchange).with("com.ac.training.routingKey1");
    }


}
