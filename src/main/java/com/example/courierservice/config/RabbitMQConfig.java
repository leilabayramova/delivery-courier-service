package com.example.courierservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_EXCHANGE = "order.exchange";

    public static final String ORDER_ASSIGNED_QUEUE = "order.assigned.queue";
    public static final String ORDER_DELIVERED_QUEUE = "order.delivered.queue";

    public static final String ORDER_ASSIGNED_ROUTING_KEY = "order.assigned";
    public static final String ORDER_DELIVERED_ROUTING_KEY = "order.delivered";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderAssignedQueue() {
        return new Queue(ORDER_ASSIGNED_QUEUE);
    }

    @Bean
    public Queue orderDeliveredQueue() {
        return new Queue(ORDER_DELIVERED_QUEUE);
    }

    @Bean
    public Binding orderAssignedBinding() {
        return BindingBuilder
                .bind(orderAssignedQueue())
                .to(orderExchange())
                .with(ORDER_ASSIGNED_ROUTING_KEY);
    }

    @Bean
    public Binding orderDeliveredBinding() {
        return BindingBuilder
                .bind(orderDeliveredQueue())
                .to(orderExchange())
                .with(ORDER_DELIVERED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}