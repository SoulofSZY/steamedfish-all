package com.szy.lamda.concurrent.threads_runnable;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈>jdk1.0〉
 *
 * @author sunzhengyu
 * @create 2019/7/10
 * @since 1.0.0
 */
public class TestThreadsAndRunnable {

    @Test
    public void test01() {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }

    @Test
    public void testDelay() throws  Exception{
        Runnable task = () -> {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println("foo " + threadName);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("bar " + threadName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread  = new Thread(task);
        thread.start();
        thread.join();
    }
}