package com.ben4;

import com.ben4.config.JedisConfig;
import com.ben4.framework.RedisDelayingQueue;
import com.ben4.framework.RedisWithReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.util.*;

/**
 * @Author: caokang
 * @Date: Created in 16:51 2020/6/30
 * @annotation:接口
 * @version:1.0
 * @TableName:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisConfig jedisConfig;


    /**
     * redisTemplate测试
     */
    @Test
    public void contextLoads() {
        log.info("redisTemplate测试开始");
        redisTemplate.opsForValue().set("key1", "ben4");
        String value = redisTemplate.opsForValue().get("key1");
        log.info(value);
    }

    @Test
    public void contextLoads2() {
        log.info("jedis测试开始");
        Jedis jedis = jedisPool.getResource();
        jedis.set("key1", "ben4");
        String value = jedis.get("key1");
        log.info(value);
        jedis.close();
        jedisPool.close();
        log.info("jedis关闭");
    }


    @Test
    public void contextLoads3() {
        Long s = 0L;
        Jedis jedis = jedisConfig.jedisPool().getResource();
        jedis.set("ck","ben4","nx","ex",5L);
        log.info("第一次参数{}" + s);
        s = jedis.setnx("ck", "ben4");
        log.info("第二次参数{}" + s);
    }

    @Test
    public void redisWithReentrantLock() {
        Jedis jedis = jedisConfig.jedisPool().getResource();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
    }

    @Test
    public void RedisDelayingQueue() {
        Jedis jedis = jedisConfig.jedisPool().getResource();
        RedisDelayingQueue queue = new RedisDelayingQueue(jedis, "q-demo");
        Thread producer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.delay("codehole" + i);
                }
            }
        };
        Thread consumer = new Thread() {
            @Override
            public void run() {
                queue.loop();
            }
        };
        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testLua(){
        Jedis jedis = jedisConfig.jedisPool().getResource();
        String lua =
                "local key = KEYS[1] " +
                        "local limit = tonumber(ARGV[1]) " +
                        "local current = tonumber(redis.call('get', key) or '0') " +
                        "if current + 1 > limit then return 0 " +
                        "else redis.call('INCRBY', key,'1')" +
                        " redis.call('expire', key,'2') " +
                        "end return 1";
        List<String> keys = new ArrayList<String>();
        keys.add("ip:limit:127.0.0.1");
        List<String> argves = new ArrayList<String>();
        argves.add("6000");
        argves.add("5");
        String luaScript = jedis.scriptLoad(lua);
        System.out.println(luaScript);
        Object object = jedis.evalsha(luaScript, keys, argves);
        System.out.println(object);
    }

}
