package com.boai.springbootrbq.Redis;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import jdk.nashorn.internal.runtime.ECMAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    private static final String UNLOCK_LUA;

    static {
        UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                "then " +
                "    return redis.call(\"del\",KEYS[1]) " +
                "else " +
                "    return 0 " +
                "end ";
    }

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static RedisTemplate redisTemplate;

    @Autowired
    public RedisLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static boolean tryLock(String lockKey, String requestId, long expireTime, TimeUnit timeUnit) {
        try {
            RedisCallback<Boolean> callback = (redisConnection) -> redisConnection.set(lockKey.getBytes(Charset.forName("UTF-8")),
                                                                                       requestId.getBytes(Charset.forName("UTF-8")),
                                                                                       Expiration.seconds(timeUnit.toSeconds(expireTime)),
                                                                                       RedisStringCommands.SetOption.SET_IF_ABSENT);
            final Boolean execute = (Boolean) redisTemplate.execute(callback);
            return execute;
        } catch (Exception e) {
            logger.info("redis lock error", e);
        }
        return false;
    }

    public static String get(String lockKey) {
        try {
            RedisCallback<String> callback = (redisConnection) -> new String(Objects.requireNonNull(redisConnection.get(lockKey.getBytes())), Charset.forName("UTF-8"));
            return (String) redisTemplate.execute(callback);
        } catch (Exception e) {
            logger.error("redis get value", e);
        }
        return null;
    }

    public static boolean releaseKey(String lockKey, String requestId) {
        RedisCallback<Boolean> callback = (redisConnection) -> redisConnection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1,
                                                                                lockKey.getBytes(Charset.forName("UTF-8")),
                                                                                requestId.getBytes(Charset.forName("UTF-8")));
        return (boolean) redisTemplate.execute(callback);
    }
}
