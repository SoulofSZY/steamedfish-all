package com.steamedfish.design.demo.behavior.observer;

/**
 * 〈观察者模式〉
 * 定义对象之间的一对多依赖，当一个对象状态改变时，它的所有依赖都会收到通知并且自动更新状态。
 *
 * 主题（Subject）是被观察的对象，而其所有依赖者（Observer）称为观察者。
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public interface Observer {

    void update(float temperature, float humidity, float pressure);
}