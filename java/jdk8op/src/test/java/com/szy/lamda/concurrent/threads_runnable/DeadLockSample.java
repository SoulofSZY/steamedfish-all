package com.szy.lamda.concurrent.threads_runnable;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试java 死锁〉
 *
 * @author sunzhengyu
 * @create 2019/8/20
 * @since 1.0.0
 */
public class DeadLockSample extends Thread {

    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1_000);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained:" + second);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
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