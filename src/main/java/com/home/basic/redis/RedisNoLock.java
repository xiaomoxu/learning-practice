package com.home.basic.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用redis做分佈式無鎖CAS
 */
public class RedisNoLock {


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(1);
        OrderService orderService = new OrderRedisServiceImpl();
        for (int i = 0; i < 10; i++) {
            service.submit(new OrderTask(latch, orderService));
        }
        latch.countDown();
        service.shutdown();
    }
}

class OrderRedisServiceImpl implements OrderService {
    static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(-1);
        jedisPoolConfig.setMaxTotal(-1);
        jedisPool = new JedisPool(jedisPoolConfig, "192.168.153.11", 6379, 0);
    }

    @Override
    public String getOrderNo() {
        try {
            Jedis jedis = jedisPool.getResource();
            SimpleDateFormat date = new SimpleDateFormat("YYYYMMDDHHMMSS");
            return date.format(new Date()) + jedis.incr("order_keys");
        } finally {
            jedisPool.close();
        }
    }
}

interface OrderService {
    public String getOrderNo();
}

class OrderTask implements Runnable {
    private CountDownLatch latch;
    private OrderService orderService;

    public OrderTask(CountDownLatch latch, OrderService orderService) {
        this.latch = latch;
        this.orderService = orderService;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("线程名%s订单号:%s\n", Thread.currentThread().getName(), orderService.getOrderNo());
    }
}