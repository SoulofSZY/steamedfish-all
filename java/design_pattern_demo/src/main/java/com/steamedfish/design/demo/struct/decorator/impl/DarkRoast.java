package com.steamedfish.design.demo.struct.decorator.impl;

import com.steamedfish.design.demo.struct.decorator.Beverage;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class DarkRoast implements Beverage {
    @Override
    public double cost() {
        return 1;
    }
}