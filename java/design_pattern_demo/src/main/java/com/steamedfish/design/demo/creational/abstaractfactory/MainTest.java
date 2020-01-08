package com.steamedfish.design.demo.creational.abstaractfactory;

import com.steamedfish.design.demo.creational.abstaractfactory.impl.ConcreateFactory1;
import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductA;
import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductB;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        AbstractFactory factory = new ConcreateFactory1();
        ProductA productA = factory.createProductA();
        ProductB productB = factory.createProductB();
        System.out.println(productA.getClass());
        System.out.println(productB.getClass());
    }
}