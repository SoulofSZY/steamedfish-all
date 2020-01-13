package com.steamedfish.design.demo.behavior.strategy;

import com.steamedfish.design.demo.behavior.strategy.impl.Quack;
import com.steamedfish.design.demo.behavior.strategy.impl.Squeak;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.setQuackBehavior(new Quack());
        duck.performQuack();
        duck.setQuackBehavior(new Squeak());
        duck.performQuack();
    }
}