package com.steamedfish.design.demo.struct.decorator;

/**
 * 〈装饰者模式〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public abstract class CondimentDecorator implements Beverage {
    protected Beverage beverage;
}