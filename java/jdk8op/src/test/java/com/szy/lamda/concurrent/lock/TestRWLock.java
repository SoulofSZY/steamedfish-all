package com.szy.lamda.concurrent.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈test j.u.c ReadWriteLock〉
 *
 * @author sunzhengyu
 * @create 2019/8/19
 * @since 1.0.0
 */
public class TestRWLock {

    private final Map<String, String> m = new TreeMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public String get(String key) {
        r.lock();
        System.out.println("读锁ing");
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    public void put(String key, String val) {
        w.lock();
        System.out.println("写锁ing");
        try {
            m.put(key, val);
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {

    }

}