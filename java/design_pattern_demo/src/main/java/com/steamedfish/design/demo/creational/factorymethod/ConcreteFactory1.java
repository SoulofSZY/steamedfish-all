package com.steamedfish.design.demo.creational.factorymethod;

import com.steamedfish.design.demo.creational.simplefactory.product.Product;
import com.steamedfish.design.demo.creational.simplefactory.product.impl.ConcreteProduct1;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class ConcreteFactory1 extends Factory {
    @Override
    protected Product factoryMethod() {
        return new ConcreteProduct1();
    }
}