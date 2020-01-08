package com.szy.lamda.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 〈1. 使用线程〉
 * callable runnable thread
 *
 * @author steamedfish
 * @create 2020/1/2
 * @since 1.0.0
 */
@Slf4j
public class TestThreadNew01 {

    @Test
    public void testRunnable() throws Exception {
        Runnable task = () -> {
            log.info("test runnable");
        };

        Thread thread = new Thread(task);
        thread.start();

        Thread.sleep(1_000);
    }

    @Test
    public void testCallable() throws Exception {
        Callable<String> task = () -> {
            log.info("test callable 1");
            return "test callable 2";
        };

        FutureTask<String> futureTask = new FutureTask<>(task);

        Thread thread = new Thread(futureTask);
        thread.start();
        log.info(futureTask.get());
    }


    @Test
    public void testThread() throws Exception {

        Thread myThread = new Thread() {
            @Override
            public void run() {
                super.run();
                log.info("test thread");
            }
        };

        myThread.run();

        Thread.sleep(1_000);
    }

}