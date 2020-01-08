package com.szy.lamda.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈2. 线程基础机制〉
 *
 * @author steamedfish
 * @create 2020/1/3
 * @since 1.0.0
 */
@Slf4j
public class TestExecutor02 {


    /**
     * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
     * <p>
     * CachedThreadPool：一个任务创建一个线程；
     * FixedThreadPool：所有任务只能使用固定大小的线程；
     * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
     */
    @Test
    public void testExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++) {
            final int taskIdx = i;
            executorService.execute(()->{
                log.info("task:{}", taskIdx);
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                log.info("sleep...");
                Thread.sleep(1_0);
            } catch (InterruptedException e) {
                log.info("error:", e);
            }
        }
    }


    @Test
    public void testDaemon() {
       Thread thread01 = new Thread("daemon thread") {
           @Override
           public void run() {
               super.run();
               log.info("thread-1...");
               try {
                   Thread.sleep(100_000);
                   log.info("thread-2...");
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }
       };
       thread01.setDaemon(true);
       thread01.start();

       log.info("main...");
       try {
           Thread.sleep(20_000);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    // yield
}