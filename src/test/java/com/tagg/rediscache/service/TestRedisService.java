package com.tagg.rediscache.service;

import com.tagg.rediscache.thread.RedisIncrementRunnable;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class TestRedisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisService redisService;

    @Test
    public void testPutMaxFileRecord() {
        redisService.putMaxFileRecord("test-aaa005.csv", "15641");
    }

    @Test
    public void testRemoveKey() {
        redisService.removeKey("test-aaa001.csv");
    }

    @Test
    public void testIncrement() {
        redisService.increment("test-aaa005.csv", 1);
    }

    @Test
    public void TestIncrementThread() {
        final long startDt = System.currentTimeMillis();
        logger.debug(Thread.currentThread().getName());
        ExecutorService executor = Executors.newFixedThreadPool(250);//creating a pool of 5 threads
        Runnable worker;
        for (int i = 0; i < 400000; i++) {
            worker = new RedisIncrementRunnable("" + i, redisService);
            executor.execute(worker);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }

        System.out.printf("Finished all threads used time %s millisec%n",(System.currentTimeMillis()-startDt));
    }

}
