package com.steamedfish.design.demo.struct.bridge.impl;

import com.steamedfish.design.demo.struct.bridge.TV;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class RCA implements TV {

    @Override
    public void on() {
        System.out.println("RCA.on");
    }

    @Override
    public void off() {
        System.out.println("RCA.off");
    }

    @Override
    public void tuneChannel() {
        System.out.println("RCA.tune");
    }
}