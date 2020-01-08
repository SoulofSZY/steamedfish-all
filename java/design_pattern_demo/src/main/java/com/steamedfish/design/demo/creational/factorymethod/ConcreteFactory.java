package com.steamedfish.design.demo.creational.factorymethod;

import com.steamedfish.design.demo.creational.simplefactory.product.Product;
import com.steamedfish.design.demo.creational.simplefactory.product.impl.ConcreteProduct;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class ConcreteFactory extends Factory {

    @Override
    protected Product factoryMethod() {
        return new ConcreteProduct();
    }
}