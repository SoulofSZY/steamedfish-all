package com.szy.lamda.concurrent.executors;

import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/11
 * @since 1.0.0
 */
public class TestLock {


    @Test
    public void test112() {
        char[] charArr = "  ".toCharArray();
        System.out.println(charArr.length);
        Map<Character, Integer> map = new HashMap<>();
        map.put(charArr[0], 0);
        map.put(charArr[1], 1);
        System.out.println(map.get(" ".toCharArray()[0]));
    }


    ExecutorService executorService;

    @After
    public void stop() {
        CommonCode.stop(executorService);
    }

    /**
     * test Semaphores
     */
    @Test
    public void testSemaphores() {
        executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    CommonCode.sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 10).forEach(i -> {
            executorService.execute(longRunningTask);
        });

        CommonCode.sleep(10);
    }

    /**
     * test StampedLock 注意不可重入
     * 1. 实现读写锁
     * 2. 实现乐观读锁  不会阻塞其他锁 相当于登记了下 不用显示释放
     * 3. 读锁升级写锁
     */
    private StampedLock stampedLock = new StampedLock();

    @Test
    public void testReadConvert2Write() {
        executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            long stamp = stampedLock.readLock();
            try {
                if (count == 0) {
                    stamp = stampedLock.tryConvertToWriteLock(stamp);
                    if (stamp == 0) {
                        System.out.println("Could not convert to write lock");
                        stamp = stampedLock.writeLock();
                    }
                    count = 23;
                }
                System.out.println(count);
            } finally {
                stampedLock.unlock(stamp);
            }
        });

        CommonCode.sleep(10);
    }

    @Test
    public void testOptimisticReadLock() {
        executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            long stamp = stampedLock.tryOptimisticRead();
            System.out.println(stamp);
            System.out.println("Optimistic Lock Valid: " + stampedLock.validate(stamp));
            CommonCode.sleep(1);
            System.out.println("Optimistic Lock Valid: " + stampedLock.validate(stamp));
            CommonCode.sleep(2);
            System.out.println("Optimistic Lock Valid: " + stampedLock.validate(stamp));

        });

        executorService.submit(() -> {
            long stamp = stampedLock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                CommonCode.sleep(2);
            } finally {
                stampedLock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        CommonCode.sleep(20);
    }

    @Test
    public void testReadWriteByStamped() {
        executorService = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();
        executorService.execute(() -> {
            long stamp = stampedLock.writeLock();
            try {
                CommonCode.sleep(10);
                map.put("foo", "bar");
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = stampedLock.readLock();
            try {
                System.out.println(map.get("foo"));
                CommonCode.sleep(5);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        };
        executorService.execute(readTask);
        executorService.execute(readTask);

        CommonCode.sleep(10);
    }


    /**
     * test readWriteLock
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Test
    public void testReadWriteLock() throws Exception {
        executorService = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        executorService.execute(() -> {
            readWriteLock.writeLock().lock();
            try {
                CommonCode.sleep(10);
                map.put("foo", "bar");
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                CommonCode.sleep(5);
            } finally {
                readWriteLock.readLock().unlock();
            }
        };
        executorService.execute(readTask);
        executorService.execute(readTask);

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * test ReentrantLock
     */
    private ReentrantLock lock = new ReentrantLock();

    void incrementSync3() {
        lock.lock();
        try {
            count += 1;
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testTryLock() {
        executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> {
            lock.lock();
            try {
                CommonCode.sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executorService.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });
    }

    /**
     * test synchronized
     */

    int count;

    public void increment() {
        count += 1;
    }

    synchronized void incrementSync() {
        count = count + 1;
    }

    void incrementSync2() {
        synchronized (this) {
            count = count + 1;
        }
    }


    @Test
    public void testConcurrent() {
        long start = System.nanoTime();
        executorService = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1000).forEach(i -> executorService.submit(this::incrementSync3));
        CommonCode.sleep(1);
        System.out.println(count);
        System.out.println("耗时：" + ((System.nanoTime() - start) / 1_000_000));
    }


}