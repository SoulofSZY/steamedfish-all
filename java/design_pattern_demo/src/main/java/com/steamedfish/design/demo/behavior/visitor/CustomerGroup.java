package com.steamedfish.design.demo.behavior.visitor;

import com.steamedfish.design.demo.behavior.visitor.Visitor;
import com.steamedfish.design.demo.behavior.visitor.impl.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class CustomerGroup {

    List<Customer> customers = new ArrayList<>();

    public void accept(Visitor visitor) {
        customers.forEach(customer -> customer.accept(visitor));
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
}