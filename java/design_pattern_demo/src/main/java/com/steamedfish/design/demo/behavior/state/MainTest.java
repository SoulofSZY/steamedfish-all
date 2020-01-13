package com.steamedfish.design.demo.behavior.state;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(2);

        gumballMachine.ejectQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();

        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();
    }
}