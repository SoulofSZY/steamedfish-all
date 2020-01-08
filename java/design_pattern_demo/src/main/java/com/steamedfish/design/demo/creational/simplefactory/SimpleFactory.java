package com.steamedfish.design.demo.creational.simplefactory;

import com.steamedfish.design.demo.creational.simplefactory.product.Product;
import com.steamedfish.design.demo.creational.simplefactory.product.impl.ConcreteProduct;
import com.steamedfish.design.demo.creational.simplefactory.product.impl.ConcreteProduct1;
import com.steamedfish.design.demo.creational.simplefactory.product.impl.ConcreteProduct2;

/**
 * 〈简单工厂模式〉
 *  实例化的操作单独放到一个类中，这个类就成为简单工厂类，让简单工厂类来决定应该用哪个具体子类来实例化。
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class SimpleFactory {

    public Product createProduct(int type) {
        if (1 == type) {
            return new ConcreteProduct1();
        } else if (2 == type) {
            return new ConcreteProduct2();
        }
        return new ConcreteProduct();
    }
}