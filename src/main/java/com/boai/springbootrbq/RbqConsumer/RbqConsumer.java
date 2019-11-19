package com.boai.springbootrbq.RbqConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RbqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RbqConsumer.class);

    @RabbitHandler
    @RabbitListener(queues = "springbootrbq")
    public void process(String message) {
        logger.info(message);
    }
}
