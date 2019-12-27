package com.szy.lamda.concurrent.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试 jdk1.0 线程〉
 *
 * @author sunzhengyu
 * @create 2019/7/15
 * @since 1.0.0
 */

public class TestThread {


    public static void testInterruptNewAndTerminated() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("这是一个线程");
        });

        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(" thread.interrupt();" + thread.isInterrupted());
        System.out.println(" thread.interrupt():" + thread.getState());

        thread.start();
        thread.join();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(" thread.interrupt();" + thread.isInterrupted());
        System.out.println(" thread.interrupt():" + thread.getState());

    }

    public static void testIntteruptRunnable() throws Exception {
        Thread thread = new Thread(() -> {
            System.out.println("这是一个线程");
            while (true) {

            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(" thread.interrupt();" + thread.isInterrupted());
        Thread.sleep(1 * 1000);
        System.out.println(" thread.interrupt();" + thread.getState());
    }

    public static void testIntteruptLockWaiting() throws Exception {
        final ReentrantLock lock = new ReentrantLock();
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            lock.lock();
            try {
                System.out.println(name + "获得锁");
                Thread.sleep(5_000);
                System.out.println(name + "线程A结束");
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }

        };
        Thread threadA = new Thread(task, "ThreadA");
        Thread threadB = new Thread(task, "ThreadB");
        threadA.setDaemon(true);
        threadB.setDaemon(true);

        threadA.start();
        threadB.start();

        Thread.sleep(1_000);
        System.out.println("threadB:" + threadB.getState());
        threadB.interrupt();
        Thread.sleep(1_000);
        System.out.println("threadB.interrupt():" + threadB.isInterrupted());
        System.out.println("threadB.interrupt():" + threadB.getState());
    }

    public static void testIntteruptBlocked() throws Exception {
        final Object lock = new Object();
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            synchronized (lock) {
                try {
                    System.out.println(name + "获得锁");
                    Thread.sleep(5_000);
                    System.out.println(name + "线程A结束");
                } catch (Exception e) {
                }
            }
        };
        Thread threadA = new Thread(task, "ThreadA");
        Thread threadB = new Thread(task, "ThreadB");
        threadA.setDaemon(true);
        threadB.setDaemon(true);

        threadA.start();
        threadB.start();

        Thread.sleep(1_000);
        System.out.println("threadB:" + threadB.getState());
        threadB.interrupt();
        Thread.sleep(1_000);
        System.out.println("threadB.interrupt():" + threadB.isInterrupted());
        System.out.println("threadB.interrupt():" + threadB.getState());
    }

    public static void testInterruptWaitingAndTimedWaiting() throws Exception {
        final Object lock = new Object();
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("这是一个线程");
                try {
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("线程被中断");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1_000);
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1_000);
        System.out.println(" thread.interrupt():" + thread.isInterrupted());
        System.out.println(" thread.interrupt():" + thread.getState());
    }


    public static void main(String[] args) throws Exception {
//        testInterruptNewAndTerminated();
//        testIntteruptRunnable();
//        testIntteruptLockWaiting();
//        testIntteruptBlocked();
        testInterruptWaitingAndTimedWaiting();
    }

}