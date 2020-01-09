package com.steamedfish.design.demo.behavior.mediator;

import com.steamedfish.design.demo.behavior.mediator.impl.Alarm;
import com.steamedfish.design.demo.behavior.mediator.impl.Calender;
import com.steamedfish.design.demo.behavior.mediator.impl.CoffeePot;
import com.steamedfish.design.demo.behavior.mediator.impl.ConcreteMediator;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class MainTest {


    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        CoffeePot coffeePot = new CoffeePot();
        Calender calender = new Calender();
        Mediator mediator = new ConcreteMediator(alarm, coffeePot, calender);
        alarm.onEvent(mediator);
        System.out.println("-------------------");
        coffeePot.onEvent(mediator);

    }
}