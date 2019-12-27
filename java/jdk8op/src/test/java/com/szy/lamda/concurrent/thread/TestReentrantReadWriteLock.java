package com.szy.lamda.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈一个读写锁的案例〉
 *
 * @author sunzhengyu
 * @create 2019/7/23
 * @since 1.0.0
 */
public class TestReentrantReadWriteLock {

    /**
     * 场景：多线程场景下 给累加器count累加，并打印当前累加的结果  1.多个线程写并读 2.多个线程只读取数据
     * <p>
     * <1.5 只能用s锁 阻塞读写
     * >1.5 用ReentrantLock 同s锁 阻塞读写
     * 本例 用ReentrantReadWriteLock 实现该功能
     */
    static final class ReadWriteCount {
        private int count;
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


        public void addCount() {
            rwLock.writeLock().lock();
            try {
                System.out.println("Thread:" + Thread.currentThread().getName() + "     开始写...    Count:" + count++);
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("Thread:" + Thread.currentThread().getName() + "     +1结束...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwLock.readLock().lock();
                rwLock.writeLock().unlock();
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + "     写完读...    Count:" + count);
            rwLock.readLock().unlock();
        }

        public void printCount() {
            rwLock.readLock().lock();
            try {
                System.out.println("Thread:" + Thread.currentThread().getName() + "         读...    Count:" + count);
            } finally {
                rwLock.readLock().unlock();
            }

        }
    }


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReadWriteCount readWriteCount = new ReadWriteCount();
        IntStream.range(0, 100).forEach(i -> {
            if (i % 2 == 0) {
                executorService.execute(() -> readWriteCount.addCount());
            } else {
                executorService.execute(() -> readWriteCount.printCount());
            }
        });

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            TimeUnit.SECONDS.sleep(5);
        }
    }

}