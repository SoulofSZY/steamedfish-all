package com.steamedfish.design.demo.struct.decorator.impl;

import com.steamedfish.design.demo.struct.decorator.Beverage;
import com.steamedfish.design.demo.struct.decorator.CondimentDecorator;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 1 + this.beverage.cost();
    }
}