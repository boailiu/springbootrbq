package com.boai.springbootrbq.Kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaTest {

    @Test
    public void sendMessageTest(){
        KafkaProducer.sendChannelMessage("helloworld","hello kafka");
    }
}
