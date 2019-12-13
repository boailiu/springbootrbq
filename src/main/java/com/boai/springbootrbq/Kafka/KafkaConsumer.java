package com.boai.springbootrbq.Kafka;

import com.boai.springbootrbq.Util.LogUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "helloworld")
    public void receiveMessage(String message) {
        LogUtil.info("receive message :" + message);
    }
}
