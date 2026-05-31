package com.example.courierservice.listener;

import com.example.courierservice.config.RabbitMQConfig;
import com.example.courierservice.event.OrderAssignedEvent;
import com.example.courierservice.event.OrderDeliveredEvent;
import com.example.courierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final CourierService courierService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_ASSIGNED_QUEUE)
    public void handleOrderAssigned(OrderAssignedEvent event) {
        courierService.markCourierAsOnDelivery(event.getCourierId());
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELIVERED_QUEUE)
    public void handleOrderDelivered(OrderDeliveredEvent event) {
        courierService.markCourierAsAvailable(event.getCourierId());
    }
}