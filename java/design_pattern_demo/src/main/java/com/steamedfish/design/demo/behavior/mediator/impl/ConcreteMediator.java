package com.steamedfish.design.demo.behavior.mediator.impl;

import com.steamedfish.design.demo.behavior.mediator.Mediator;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class ConcreteMediator extends Mediator {

    private Alarm alarm;
    private CoffeePot coffeePot;
    private Calender calender;

    public ConcreteMediator(Alarm alarm, CoffeePot coffeePot, Calender calender) {
        this.alarm = alarm;
        this.coffeePot = coffeePot;
        this.calender = calender;
    }

    @Override
    public void doEvent(String eventType) {
        switch (eventType) {
            case "alarm":
                doAlarmEvent();
                break;
            case "calender":
                doCalenderEvent();
                break;
            default:
                doCoffeePotEvent();
        }
    }

    private void doAlarmEvent() {
        alarm.doAlarm();
        calender.doCalender();
    }

    private void doCalenderEvent() {
        calender.doCalender();
        alarm.doAlarm();
        coffeePot.doCoffeePot();
    }

    private void doCoffeePotEvent() {
        coffeePot.doCoffeePot();
    }
}