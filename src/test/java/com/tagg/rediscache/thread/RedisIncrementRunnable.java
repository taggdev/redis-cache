package com.tagg.rediscache.thread;

import com.tagg.rediscache.service.RedisService;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisIncrementRunnable implements Runnable {

    private RedisService redisService;

    private String message;

    public RedisIncrementRunnable(String message){
        this.message=message;
    }

    public RedisIncrementRunnable(String message, RedisService redisService){
        this.message=message;
        this.redisService = redisService;
    }

    public void run() {
        System.out.printf("%s : %s (Start) message = %s%n",new Date(), Thread.currentThread().getName(),message);
        processMessage();//call processMessage method that sleeps the thread for 2 seconds
        System.out.printf("%s : %s (End)%n",new Date(), Thread.currentThread().getName());//prints thread name
    }

    private void processMessage() {
        try {
            redisService.increment("test-aaa005.csv", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RedisService redisService;
        ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads
        Runnable worker;
        for (int i = 0; i < 5; i++) {
            worker = new RedisIncrementRunnable("" + i);
            executor.execute(worker);//calling execute method of ExecutorService
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }

        System.out.println("Finished all threads");
    }

}
