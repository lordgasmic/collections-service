package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.wine.models.WineImageTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${lordgasmic.rabbitmq.exchange}")
    private String exchange;

    @Value("${lordgasmic.rabbitmq.routingKey}")
    private String routingKey;

    public void send(final WineImageTransfer transfer) {
        rabbitTemplate.convertAndSend(exchange, routingKey, transfer);
        log.info("Send msg = " + transfer);
    }
}
