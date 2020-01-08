package com.steamedfish.design.demo.creational;

/**
 * 〈单例模式〉
 *  饿汉/懒汉模式
 *  双检锁
 *  枚举
 *  借助jvm加载类的机制
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class Singleton {
    private Singleton() {}

    private static class LazyHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}