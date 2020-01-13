package com.steamedfish.design.demo.behavior.template.impl;

import com.steamedfish.design.demo.behavior.template.CaffeineBeverage;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class Coffee extends CaffeineBeverage {

    @Override
    protected void brew() {
        System.out.println("Coffee.brew");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }
}