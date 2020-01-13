package com.steamedfish.design.demo.behavior.state;

import com.steamedfish.design.demo.behavior.state.impl.HasQuarterState;
import com.steamedfish.design.demo.behavior.state.impl.NoQuarterState;
import com.steamedfish.design.demo.behavior.state.impl.SoldOutState;
import com.steamedfish.design.demo.behavior.state.impl.SoldState;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public class GumballMachine {

    private State hasQuarterState;
    private State noQuarterState;
    private State soldOutState;
    private State soldState;

    private State state;
    private int count;

    public GumballMachine(int numberGumballs) {
        this.count = numberGumballs;

        this.hasQuarterState = new HasQuarterState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.soldOutState = new SoldOutState(this);
        this.soldState = new SoldState(this);

        if (this.count > 0) {
            this.state = noQuarterState;
        } else {
            this.state = soldOutState;
        }

    }

    public void insertQuarter() {
        state.insertQuarter();
    }
    public void ejectQuarter() {
        state.ejectQuarter();
    }
    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count -= 1;
        }
    }
}