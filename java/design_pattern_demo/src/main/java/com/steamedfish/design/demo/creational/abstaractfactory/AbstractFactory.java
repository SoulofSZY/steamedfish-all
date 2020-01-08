package com.steamedfish.design.demo.creational.abstaractfactory;

import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductA;
import com.steamedfish.design.demo.creational.abstaractfactory.product.ProductB;

/**
 * 〈抽象工厂模式〉
 *  抽象工厂模式创建的是对象家族，也就是很多对象而不是一个对象，并且这些对象是相关的，也就是说必须一起创建出来
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public abstract class AbstractFactory {

    protected abstract ProductA createProductA();

    protected abstract ProductB createProductB();
}