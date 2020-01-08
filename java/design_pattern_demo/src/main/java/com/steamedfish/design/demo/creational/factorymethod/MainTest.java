package com.steamedfish.design.demo.creational.factorymethod;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory2();
        factory.doSomething();
    }
}