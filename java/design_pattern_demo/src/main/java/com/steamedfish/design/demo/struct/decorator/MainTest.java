package com.steamedfish.design.demo.struct.decorator;

import com.steamedfish.design.demo.struct.decorator.impl.DarkRoast;
import com.steamedfish.design.demo.struct.decorator.impl.Milk;
import com.steamedfish.design.demo.struct.decorator.impl.Mocha;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();
        beverage = new Milk(beverage);
        beverage = new Mocha(beverage);
        System.out.println(beverage.cost());
    }
}