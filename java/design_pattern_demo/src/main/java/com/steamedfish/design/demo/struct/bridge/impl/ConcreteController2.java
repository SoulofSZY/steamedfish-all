package com.steamedfish.design.demo.struct.bridge.impl;

import com.steamedfish.design.demo.struct.bridge.RemoteControl;
import com.steamedfish.design.demo.struct.bridge.TV;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class ConcreteController2 extends RemoteControl {

    public ConcreteController2(TV tv) {
        super(tv);
    }

    @Override
    protected void on() {
        System.out.println("ConcreteController2.on");
        tv.on();
    }

    @Override
    protected void off() {
        System.out.println("ConcreteController2.off");
        tv.off();
    }

    @Override
    protected void tuneChannel() {
        System.out.println("ConcreteController2.tune");
        tv.tuneChannel();
    }
}