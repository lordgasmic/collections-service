package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.wine.models.WineImageTransfer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "${javainuse.rabbitmq.queue}")
    public void recievedMessage(final WineImageTransfer transfer) {
        System.out.println("Recieved Message From RabbitMQ " + transfer);
    }
}
