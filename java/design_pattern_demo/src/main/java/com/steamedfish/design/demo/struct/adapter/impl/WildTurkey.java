package com.steamedfish.design.demo.struct.adapter.impl;

import com.steamedfish.design.demo.struct.adapter.Turkey;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("gobble!");
    }
}