package com.steamedfish.design.demo.behavior.observer.impl;

import com.steamedfish.design.demo.behavior.observer.Observer;
import com.steamedfish.design.demo.behavior.observer.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class WeatherData implements Subject {

    private List<Observer> observerList;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observerList = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (observerList.contains(observer)) {
            return;
        }
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(observer -> {
            observer.update(this.temperature, this.humidity, this.pressure);
        });
    }
}