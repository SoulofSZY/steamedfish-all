package com.szy.lamda.lang.ref;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.ref.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈 测试jdk-lang ref包 〉
 *
 * @author sunzhengyu
 * @create 2019/9/17
 * @since 1.0.0
 */
@Slf4j
public class TestRef {

    /**
     * threadLocal 中使用了WeakReference   threadLocal相当于一个key  同时维护了一些列操作其值的接口， 而实际的值map引用 由实际线程维护
     * 每个线程有自己的threadLocalMap 相当于动态的给线程添加了一些线程安全的数据
     */
    @Test
    public void testThreadLocal() {
        Thread thread = new Thread(()->{
            ThreadLocal<String> local1 = new ThreadLocal();
            local1.set("test1");
            log.info(local1.get());
            System.gc();
            log.info(local1.get());
            local1.remove();
            local1 = null; // 这时侯可能存在内存泄漏的问题
        });

    }

    @Test
    public void testPhantomReference() {
        Object counter = new Object();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
        counter = null;
        System.gc();
        try {
            // Remove是一个阻塞方法，可以指定timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                // do something
            }
        } catch (InterruptedException e) {
            // Handle it
        }
    }

    @Test
    public void testWeakReference() {
        PrintObj counter = new PrintObj();
        ReferenceQueue refQueue = new ReferenceQueue<>();
        WeakReference<Object> p = new WeakReference<>(counter, refQueue);
        counter = null;
        System.out.println(p.get());
        System.gc();
        try {
            // Remove是一个阻塞方法，可以指定timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                System.out.println(ref.get());
            }
        } catch (InterruptedException e) {
            // Handle it
        }
    }

    @Test
    public void testSoftReference() {
        byte[] arr = new byte[1024*65];
        ReferenceQueue refQueue = new ReferenceQueue<>();
        SoftReference<Object> p = new SoftReference<>(arr, refQueue);
        arr = null;
        System.out.println(p.get());
        System.gc();
        System.out.println(p.get());
        try {
            // Remove是一个阻塞方法，可以指定timeout，或者选择一直阻塞
            Reference<Object> ref = refQueue.remove(1000L);
            if (ref != null) {
                System.out.println(ref.get());
            }
        } catch (InterruptedException e) {
            // Handle it
        }
    }


}