package com.boai.springbootrbq.RbqConsumer;

import com.boai.springbootrbq.Mapper.LogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbqConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RbqConsumer.class);

    private LogMapper logMapper;

    @Autowired
    public RbqConsumer(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @RabbitHandler
    @RabbitListener(queues = "springbootrbq")
    public void process(String message) {
//        logger.info(message);
        logMapper.saveLog(message);
    }
}
