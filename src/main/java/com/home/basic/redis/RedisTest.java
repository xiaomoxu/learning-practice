package com.home.basic.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisTest {
    @Test
    public void testConnRedis() {
        // 连接到Redis数据库(如果连接超时，可能是网络的问题，也可能是端口没有开)
        Jedis jedis = new Jedis("192.168.153.11", 6379);
//        jedis.auth("redis123");
        // 存储数据和获取数据 (存进去是什么，取出来也是什么)
        jedis.set("username", "陈如水");
        String username = jedis.get("username");
        System.out.println(username);
    }

}
