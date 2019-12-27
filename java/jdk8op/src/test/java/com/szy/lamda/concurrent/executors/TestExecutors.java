package com.szy.lamda.concurrent.executors;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈>jdk1.5〉
 *
 * @author sunzhengyu
 * @create 2019/7/10
 * @since 1.0.0
 */
public class TestExecutors {

    ExecutorService executorService;

    @After
    public void stop() {
        CommonCode.stop(executorService);
    }

    @Test
    public void test01() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
                String name = Thread.currentThread().getName();
                System.out.println("hello " + name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testCallableAndFuture() throws Exception {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(task);
        System.out.println("future done? " + future.isDone());

        Integer result = future.get(1, TimeUnit.SECONDS);

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);
    }


    @Test
    public void testInvokeAll() throws Exception {
        executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        executorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }

    @Test
    public void testInvokeAny() throws Exception {
        executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 2),
                callable("task3", 2));

        String result = executorService.invokeAny(callables);

        System.out.println(result);
    }

    Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }


    @Test
    public void testSchedule() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        this.executorService = executorService;

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executorService.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(500);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms%n", remainingDelay);
    }

    /**
     *  固定周期调用  如果任务执行2s 周期为1s 那么任务执行间不会有间隔
     * @throws Exception
     */
    @Test
    public void testFixRate() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        this.executorService = executorService;

        Runnable task = () -> {
            System.out.println("Scheduling: " + System.nanoTime());
        };

        int initialDelay = 0;
        int period = 1;
        executorService.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        Thread.sleep(10 * 1000);
    }

    /**
     * 任务之间的执行 间隔为delay
     * @throws Exception
     */
    @Test
    public void testFixDelay() throws Exception{
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        this.executorService = executorService;

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executorService.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(10 * 1000);
    }
}