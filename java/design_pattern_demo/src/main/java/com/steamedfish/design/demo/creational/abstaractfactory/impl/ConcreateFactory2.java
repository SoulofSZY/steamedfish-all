package com.steamedfish.design.demo.creational.abstaractfactory.impl;

import com.steamedfish.design.demo.creational.abstaractfactory.AbstractFactory;
import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductA;
import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductB;
import com.steamedfish.design.demo.creational.abstaractfactory.product.impl.ConcreteProductA1;
import com.steamedfish.design.demo.creational.abstaractfactory.product.impl.ConcreteProductA2;
import com.steamedfish.design.demo.creational.abstaractfactory.product.impl.ConcreteProductB1;
import com.steamedfish.design.demo.creational.abstaractfactory.product.impl.ConcreteProductB2;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class ConcreateFactory2 extends AbstractFactory {
    @Override
    protected ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    protected ProductB createProductB() {
        return new ConcreteProductB2();
    }
}