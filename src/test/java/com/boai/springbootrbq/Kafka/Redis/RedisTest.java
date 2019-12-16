package com.boai.springbootrbq.Kafka.Redis;

import com.boai.springbootrbq.Redis.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void setTest() {
        redisTemplate.opsForValue().set("testKey", "testData", 100L, TimeUnit.SECONDS);
    }

    @Test
    public void setTest2(){
        final boolean b = RedisLock.tryLock("testKey3", "testData3", 100L, TimeUnit.SECONDS);
        System.out.println(b);
        final String testKey2 = RedisLock.get("testKey3");
        System.out.println(testKey2);
        final boolean b1 = RedisLock.releaseKey("testKey3", "testData3");
        System.out.println(b1);

    }
}
