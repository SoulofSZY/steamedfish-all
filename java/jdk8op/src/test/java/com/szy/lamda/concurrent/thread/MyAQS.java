package com.szy.lamda.concurrent.thread;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * 〈一句话功能简述〉<br>
 * 〈手抄 jdk AQS〉
 *  尽量简化一下，理解为什么需要AQS，如何使用AQS， 至少 要做什么，再进一步结合JDK源代码中的实践，理解AQS的原理与应用
 * @author sunzhengyu
 * @create 2019/7/30
 * @since 1.0.0
 */
public class MyAQS {


    /**
     * 链表队列
     */
    static final class Node {

        /**
         * 标记节点在共享模式下等待
         */
        static final Node SHARED = new Node();
        /**
         * 标记节点在排他模式下等待
         */
        static final Node EXCLUSIVE = null;
        /**
         * 等待状态： 线程撤销标记
         */
        static final int CANCELLED = 1;
        /**
         * 等待状态：继任节点需要执行
         */
        static final int SIGNAL = -1;
        /**
         * 等待状态：线程在等待唤醒条件
         */
        static final int CONDITION = -2;
        /**
         * 等待状态：共享节点传播
         */
        static final int PROPAGATE = -3;


        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (null == p)
                throw new NullPointerException();
            else
                return p;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {    // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) {   // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }

    }

    public class ConditionObject implements Condition, Serializable {


        private static final long serialVersionUID = 3944255671344887835L;

        private transient Node firstWaiter;
        private transient Node lastWaiter;

        public ConditionObject() {
        }

        private Node addConditionWaiter() {

            Node t = lastWaiter;
            if (null != t && t.waitStatus != Node.CONDITION) {
                unlinkCancelledWaiters();
                t = lastWaiter;
            }

            Node node = new Node(Thread.currentThread(), Node.CONDITION);
            if (t == null) {
                firstWaiter = node;
            } else {
                t.nextWaiter = node;
            }

            lastWaiter = node;
            return node;
        }

        private void unlinkCancelledWaiters() {
            Node t = firstWaiter;
            Node trail = null;
            while (t != null) {
                Node next = t.nextWaiter;
                if (t.waitStatus != Node.CONDITION) {
                    t.nextWaiter = null;
                    if (trail == null) {
                        firstWaiter = next;
                    } else {
                        trail.nextWaiter = next;
                    }
                    if (next == null) {
                        lastWaiter = null;
                    }
                } else {
                    trail = t;
                }
                t = next;
            }
        }

        final boolean transferForSignal(Node node) {

            return false;

        }

        @Override
        public void await() throws InterruptedException {

        }

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

        }

        @Override
        public void signalAll() {

        }
    }

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long waitStatusOffset;

    static {
        try {
            waitStatusOffset = unsafe.objectFieldOffset
                    (MyAQS.Node.class.getDeclaredField("waitStatus"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private static final boolean compareAndSetWaitStatus(Node node, int expect, int update) {

        return unsafe.compareAndSwapObject(node, waitStatusOffset, expect, update);
    }

}