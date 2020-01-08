package com.steamedfish.design.demo.creational.simplefactory;

import com.steamedfish.design.demo.creational.simplefactory.product.Product;

/**
 * 〈测试类〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        Product product = factory.createProduct(1);
        System.out.println(product.getClass());
    }
}