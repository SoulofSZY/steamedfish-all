package com.steamedfish.design.demo.behavior.template.impl;

import com.steamedfish.design.demo.behavior.template.CaffeineBeverage;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class Tea extends CaffeineBeverage {

    @Override
    protected void brew() {
        System.out.println("Tea.brew");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Tea.addCondiments");
    }
}