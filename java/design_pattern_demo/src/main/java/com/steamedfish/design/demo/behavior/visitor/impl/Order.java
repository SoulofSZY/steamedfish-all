package com.steamedfish.design.demo.behavior.visitor.impl;

import com.steamedfish.design.demo.behavior.visitor.Element;
import com.steamedfish.design.demo.behavior.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Order implements Element {

    private String name;
    private List<Item> items = new ArrayList<>();

    public Order(String name) {
        this.name = name;
    }

    public Order(String name, String itemName) {
        this.name = name;
        this.addItem(new Item(itemName));
    }

    public String getName() {
        return name;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        items.forEach(item -> item.accept(visitor));
    }
}