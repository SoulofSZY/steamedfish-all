package com.steamedfish.design.demo.struct.composite.impl;

import com.steamedfish.design.demo.struct.composite.Component;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Leaf extends Component {

    public Leaf(String name) {
        super(name);
    }

    @Override
    public void print(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }

        System.out.println(this.name);
    }

    @Override
    protected void add(Component component) {

    }

    @Override
    protected void remove(Component component) {

    }
}