package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.wine.models.WineImageTransfer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(final WineImageTransfer transfer) {
        rabbitTemplate.convertAndSend(exchange, routingkey, transfer);
        System.out.println("Send msg = " + transfer);
    }
}
