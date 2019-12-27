package com.szy.lamda.concurrent.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈lock.newCondition〉
 *
 * @author sunzhengyu
 * @create 2019/7/20
 * @since 1.0.0
 */
public class TestCondition {


    /**
     * BoundedBuffer 是一个定长100的集合，当集合中没有元素时，take方法需要等待，直到有元素时才返回元素
     * 当其中的元素数达到最大值时，要等待直到元素被take之后才执行put的操作
     */
    static final class BoundedBuffer {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

        final Object[] items = new Object[100];
        int putIdx, takeIdx, count;

        public void put(Object x) throws InterruptedException {
            System.out.println("put wait lock");
            lock.lock();
            System.out.println("put acquire lock");

            try {
                while (count == items.length) {
                    System.out.println("buffer full, waiting...");
                    notFull.await();
                }

                items[putIdx] = x;
                if (++putIdx == items.length) {
                    putIdx = 0;
                }
                count++;

                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            System.out.println("take wait lock");
            lock.lock();
            System.out.println("take acquire lock");

            try {
                while (count == 0) {
                    System.out.println("buffer empty, waiting...");
                    notEmpty.await();
                }

                Object x = items[takeIdx];
                if (++takeIdx == items.length) {
                    takeIdx = 0;
                }
                count--;

                notFull.signal();
                return x;
            } finally {
                lock.unlock();
            }
        }
    }



    public static void main(String[] args) {
        final BoundedBuffer boundedBuffer = new BoundedBuffer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 run");
                for (int i=0;i<1000;i++) {
                    try {
                        System.out.println("putting..");
                        boundedBuffer.put(Integer.valueOf(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }) ;

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++) {
                    try {
                        Object val = boundedBuffer.take();
                        System.out.println(val);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }) ;

        t1.start();
        t2.start();
    }

}