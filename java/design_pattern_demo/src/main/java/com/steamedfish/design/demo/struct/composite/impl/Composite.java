package com.steamedfish.design.demo.struct.composite.impl;


import com.steamedfish.design.demo.struct.composite.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Composite extends Component {

    private List<Component> children;

    public Composite(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    @Override
    public void print(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(this.name);
        children.forEach(component -> component.print(level + 1));
    }

    @Override
    public void add(Component component) {
        this.children.add(component);
    }

    @Override
    public void remove(Component component) {
        this.children.remove(component);
    }
}