package com.steamedfish.design.demo.struct.adapter;

import com.steamedfish.design.demo.struct.adapter.impl.WildTurkey;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class MainTest {


    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);

        duck.quack();
    }
}