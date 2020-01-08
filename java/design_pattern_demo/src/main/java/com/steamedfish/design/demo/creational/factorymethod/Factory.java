package com.steamedfish.design.demo.creational.factorymethod;

import com.steamedfish.design.demo.creational.simplefactory.product.Product;

/**
 * 〈工厂方法模式〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public abstract class Factory {

    protected abstract Product factoryMethod();

    public void doSomething() {
        Product product = this.factoryMethod();
        System.out.println(product.getClass());
    }
}