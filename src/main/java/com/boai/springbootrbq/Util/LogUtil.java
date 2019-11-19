package com.boai.springbootrbq.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUtil{

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static RabbitTemplate rabbitTemplate;

    @Autowired
    public LogUtil(RabbitTemplate rabbitTemplate) {
        LogUtil.rabbitTemplate = rabbitTemplate;
    }

    public static void info(String s){
        logger.info(s);
        rabbitTemplate.convertAndSend("springbootrbq",s);
    }
}
