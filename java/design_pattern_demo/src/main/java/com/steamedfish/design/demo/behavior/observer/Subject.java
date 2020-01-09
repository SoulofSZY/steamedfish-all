package com.steamedfish.design.demo.behavior.observer;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
