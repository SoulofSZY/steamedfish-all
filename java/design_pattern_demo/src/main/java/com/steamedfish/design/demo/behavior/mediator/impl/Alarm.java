package com.steamedfish.design.demo.behavior.mediator.impl;

import com.steamedfish.design.demo.behavior.mediator.Colleague;
import com.steamedfish.design.demo.behavior.mediator.Mediator;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class Alarm extends Colleague {

    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("alarm");
    }

    public void doAlarm() {
        System.out.println("doAlarm()");
    }
}