package com.steamedfish.design.demo.behavior.visitor;

import com.steamedfish.design.demo.behavior.visitor.impl.Customer;
import com.steamedfish.design.demo.behavior.visitor.impl.Item;
import com.steamedfish.design.demo.behavior.visitor.impl.Order;

/**
 * 〈访问者模式〉<br>
 *  为一个对象结构（比如组合结构）增加新能力
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public interface Visitor {

    void visit(Customer customer);
    void visit(Order order);
    void visit(Item item);
}
