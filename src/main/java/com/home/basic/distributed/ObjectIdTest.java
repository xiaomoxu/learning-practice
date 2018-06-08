package com.home.basic.distributed;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectIdTest {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++)
            executorService.submit((Runnable) () -> {
                ObjectId objectId = ObjectId.get();
                System.out.println(objectId.toString());
                countDownLatch.countDown();
            });
        countDownLatch.await();
        System.out.println("finish");
        executorService.shutdown();
    }
}
