package com.steamedfish.design.demo.behavior.strategy;

import java.util.Optional;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class Duck {

    private QuackBehavior quackBehavior;

    public void performQuack() {
        Optional.ofNullable(quackBehavior).ifPresent(behavior -> {
            behavior.quack();
        });
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}