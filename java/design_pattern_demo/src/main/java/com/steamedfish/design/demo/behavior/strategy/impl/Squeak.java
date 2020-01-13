package com.steamedfish.design.demo.behavior.strategy.impl;

import com.steamedfish.design.demo.behavior.strategy.QuackBehavior;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class Squeak implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("squeak");
    }
}