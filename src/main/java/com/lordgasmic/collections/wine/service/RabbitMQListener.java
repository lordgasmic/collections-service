package com.lordgasmic.collections.wine.service;

import com.lordgasmic.collections.wine.models.WineImageTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQListener {

    @RabbitListener(queues = "${lordgasmic.rabbitmq.queue}")
    public void recievedMessage(final WineImageTransfer transfer) {
        log.info("Recieved Message From RabbitMQ " + transfer);
    }
}
