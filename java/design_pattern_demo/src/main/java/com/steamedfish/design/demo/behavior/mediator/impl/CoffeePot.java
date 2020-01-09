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
public class CoffeePot extends Colleague {
    @Override
    public void onEvent(Mediator mediator) {
        mediator.doEvent("coffeePot");
    }

    public void doCoffeePot() {
        System.out.println("doCoffeePot()");
    }
}