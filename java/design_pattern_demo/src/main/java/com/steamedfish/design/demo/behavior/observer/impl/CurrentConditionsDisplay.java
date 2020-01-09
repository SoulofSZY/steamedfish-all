package com.steamedfish.design.demo.behavior.observer.impl;

import com.steamedfish.design.demo.behavior.observer.Observer;
import com.steamedfish.design.demo.behavior.observer.Subject;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class CurrentConditionsDisplay implements Observer {

    public CurrentConditionsDisplay(Subject subject) {
        subject.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("CurrentConditionsDisplay.update: " + temperature + " " + humidity + " " + pressure);
    }
}