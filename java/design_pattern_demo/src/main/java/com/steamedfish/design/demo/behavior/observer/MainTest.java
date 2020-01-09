package com.steamedfish.design.demo.behavior.observer;

import com.steamedfish.design.demo.behavior.observer.impl.CurrentConditionsDisplay;
import com.steamedfish.design.demo.behavior.observer.impl.StatisticsDisplay;
import com.steamedfish.design.demo.behavior.observer.impl.WeatherData;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
    }
}