package com.steamedfish.design.demo.behavior.template;

import com.steamedfish.design.demo.behavior.template.impl.Tea;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        CaffeineBeverage caffeineBeverage = new Tea();
        caffeineBeverage.prepareRecipe();
    }
}