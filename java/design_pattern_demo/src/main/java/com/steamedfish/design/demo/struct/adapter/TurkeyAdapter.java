package com.steamedfish.design.demo.struct.adapter;

/**
 * 〈适配器模式〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        this.turkey.gobble();
    }
}