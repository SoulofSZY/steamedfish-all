package com.szy.lamda.concurrent.thread.queue.delayqueue;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈向缓存添加内容时，给每一个key设定过期时间，系统自动将超过过期时间的key清除〉
 * 细节点：
 * 1. 当向缓存中添加key-value对时，如果这个key在缓存中存在并且还没有过期，需要用这个key对应的新过期时间
 * 2. 为了能够让DelayQueue将其已保存的key删除，需要重写实现Delayed接口添加到DelayQueue的DelayedItem的hashCode函数和equals函数
 * 3. 当缓存关闭，监控程序也应关闭，因而监控线程应当用守护线程
 *
 * @author sunzhengyu
 * @create 2019/8/9
 * @since 1.0.0
 */
public class DelayQueueCase {


    private static class Cache<K, V> {
        private static Object obj = new Object();
        private ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<>(100);
        private DelayQueue<DelayItem<K>> queue = new DelayQueue<>();

        public static Cache cache;


        private Cache() {
            Thread t = new Thread() {
                @Override
                public void run() {
                    dameonCheckOverdueKey();
                }
            };
            t.setDaemon(true);
            t.start();
        }

        public static Cache getCache() {
            if (null == cache) {
                synchronized (obj) {
                    if (null == cache) {
                        cache = new Cache();
                    }
                }
            }
            return cache;
        }

        public void put(K k, V v, long liveTime, TimeUnit timeUnit) {
            V v2 = cacheMap.put(k, v);
            DelayItem<K> tmpItem = new DelayItem<>(k, liveTime, timeUnit);
            if (v2 != null) {
                queue.remove(tmpItem);
            }
            queue.put(tmpItem);
        }

        private void dameonCheckOverdueKey() {
            while (true) {
                DelayItem<K> delayedItem = queue.poll();
                if (delayedItem != null) {
                    cacheMap.remove(delayedItem.getVal());
                    System.out.println(System.nanoTime() + " remove " + delayedItem.getVal() + " from cache");
                }
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private class DelayItem<T> implements Delayed {

            private T val;
            private long liveTime;
            private long expireTime;

            public DelayItem(T val, long liveTime, TimeUnit timeUnit) {
                this.val = val;
                this.liveTime = liveTime;
                this.expireTime = (null == timeUnit ? liveTime : TimeUnit.NANOSECONDS.convert(liveTime, timeUnit)) + System.nanoTime();
            }

            @Override
            public long getDelay(TimeUnit unit) {
                if (TimeUnit.NANOSECONDS.equals(unit)) return expireTime - System.nanoTime();
                return unit.convert(expireTime - System.nanoTime(), TimeUnit.NANOSECONDS);
            }

            @Override
            public int compareTo(Delayed o) {
                if (null == o) return 1;
                if (this == o) return 0;

                long diff = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);

                return diff > 0 ? 1 : diff == 0 ? 0 : -1;
            }


            public T getVal() {
                return val;
            }

            public void setVal(T val) {
                this.val = val;
            }

            public long getLiveTime() {
                return liveTime;
            }

            public void setLiveTime(long liveTime) {
                this.liveTime = liveTime;
            }

            public long getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(long expireTime) {
                this.expireTime = expireTime;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                DelayItem<?> delayItem = (DelayItem<?>) o;
                return Objects.equals(val, delayItem.val);
            }

            @Override
            public int hashCode() {

                return Objects.hash(val);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Cache cache = Cache.getCache();

        IntStream.range(0, 10).forEach(i -> {
            i++;
            cache.put("key" + i, "val" + i, 10, TimeUnit.SECONDS);
        });

        Thread.sleep(50_000);
    }

}