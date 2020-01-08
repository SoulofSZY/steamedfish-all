package com.szy.lamda.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 〈3. 中断〉
 * 一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束。
 *
 * @author steamedfish
 * @create 2020/1/3
 * @since 1.0.0
 */
@Slf4j
public class TestInterrupte03 {


    /**
     * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛出 InterruptedException，从而提前结束该线程。
     * 但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
     */
    @Test
    public void testInterrupte() {
        Thread thread = new Thread(() -> {
            log.info("thread...");
            try {
                Thread.sleep(10_000);
                log.info("thread...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.interrupt();

        log.info("mian up");
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么调用线程的 interrupt() 方法就无法使线程提前结束。
     * <p>
     * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程
     */
    @Test
    public void testIntterrupted() {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < 100_0000) {
                if (Thread.currentThread().isInterrupted()) {
                    log.info("thread already interrupted!");
                    return;
                }
                log.info("thread:{}", i++);
            }
            log.info("over...");
        });
        thread.start();
        thread.interrupt();

        log.info("main up");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executor 中断操作 ：  shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法
     */
    @Test
    public void testExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(10_000);
                log.info("thread up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1_00);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 中断线程池特定线程
     */
    @Test
    public void testExecutorAppointThread() {
        List<Future<?>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int taskIdx = i;
            Future<?> future = executorService.submit(() -> {
                try {
                    Thread.sleep(1_000);
                    log.info("thread:{}", taskIdx);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            futureList.add(future);
        }

        futureList.get(0).cancel(true);

        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}