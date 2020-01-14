package com.steamedfish.design.demo.behavior.visitor.impl;

import com.steamedfish.design.demo.behavior.visitor.Element;
import com.steamedfish.design.demo.behavior.visitor.Visitor;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Item implements Element {

    private String name;

    public Item(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}