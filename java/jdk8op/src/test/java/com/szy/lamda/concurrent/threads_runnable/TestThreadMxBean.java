package com.szy.lamda.concurrent.threads_runnable;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/8/20
 * @since 1.0.0
 */
public class TestThreadMxBean {

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

        Runnable dlCheck = () -> {

            long[] threadIds = mxBean.findDeadlockedThreads();
            if (null != threadIds) {
                ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                System.out.println("Detected Dead Threads:");
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println(threadInfo.getThreadName());
                }
            }
        };

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(dlCheck, 5, 10, TimeUnit.SECONDS);

        String lockA = "lockA";
        String lockB = "lockB";

        DeadLockSample t1 = new DeadLockSample("ThreadA", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("ThreadB", lockB, lockA);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}