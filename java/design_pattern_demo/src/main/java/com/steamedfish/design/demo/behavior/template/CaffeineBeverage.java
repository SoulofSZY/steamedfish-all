package com.steamedfish.design.demo.behavior.template;

/**
 * 〈模板方法模式〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public abstract class CaffeineBeverage {

    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("boilWater");
    }

    protected abstract void brew();

    private void pourInCup() {
        System.out.println("pourInCup");
    }

    protected abstract void addCondiments();
}