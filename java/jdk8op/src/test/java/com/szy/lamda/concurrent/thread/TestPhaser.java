package com.szy.lamda.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈jdk1.7 Phaser线程同步器〉
 *
 * @author sunzhengyu
 * @create 2019/7/30
 * @since 1.0.0
 */
public class TestPhaser {

    public static void testPhaser() throws Exception {
        Phaser phaser = new Phaser();
        // 主线程注册
        // 主线程可以等待所有的parties到达后再解除阻塞（类似与CountDownLatch）
        phaser.register();

        ExecutorService executorService = Executors.newFixedThreadPool(12);
        IntStream.range(0, 12).forEach(
                i -> {
                    //每创建一个task，注册一个party
                    phaser.register();
                    executorService.execute(() -> {
                        try {
                            int num = 0;
                            while (num < 3 && !phaser.isTerminated()) {
                                System.out.println("Generation:" + phaser.getPhase());
                                Thread.sleep(3000);
                                //等待同一周期内，其他Task到达
                                //然后进入新的周期，并继续同步进行
                                phaser.arriveAndAwaitAdvance();
                                num++;//我们假定，运行三个周期即可
                            }
                        } catch (Exception e) {
                        } finally {
                            phaser.arriveAndDeregister();
                        }
                    });
                }
        );

        Thread.sleep(10_000);
        System.out.println(phaser.arriveAndDeregister());

    }


    public static void main(String[] args) throws Exception {
        testPhaser();
    }
}