package com.szy.lamda.concurrent.lock;

import java.util.Dictionary;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.StampedLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/8/19
 * @since 1.0.0
 */
public class TestStampedLock {
    private final Map<String, String> m = new TreeMap<>();
    private final StampedLock lock = new StampedLock();

    public void mutate(String key, String val) {
        long stamp = lock.writeLock();
        try {
            m.put(key, val);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String access(String key) {
        long stamp = lock.tryOptimisticRead();
        String data = m.get(key);

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                data = m.get(key);
            } finally {
                lock.unlockRead(stamp);
            }
        }

        return data;
    }

}