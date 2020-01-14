package com.steamedfish.design.demo.struct.bridge.impl;

import com.steamedfish.design.demo.struct.bridge.TV;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Sony implements TV {

    @Override
    public void on() {
        System.out.println("Sony.on");
    }

    @Override
    public void off() {
        System.out.println("Sony.off");
    }

    @Override
    public void tuneChannel() {
        System.out.println("Sony.tune");
    }
}