package com.steamedfish.design.demo.behavior.state.impl;

import com.steamedfish.design.demo.behavior.state.GumballMachine;
import com.steamedfish.design.demo.behavior.state.State;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class HasQuarterState implements State {

    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        gumballMachine.setState(gumballMachine.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}