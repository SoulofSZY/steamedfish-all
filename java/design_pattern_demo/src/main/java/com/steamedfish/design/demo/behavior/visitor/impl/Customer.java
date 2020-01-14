package com.steamedfish.design.demo.behavior.visitor.impl;

import com.steamedfish.design.demo.behavior.visitor.Element;
import com.steamedfish.design.demo.behavior.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Customer implements Element {

    private String name;
    private List<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        orders.forEach(order -> order.accept(visitor));
    }
}