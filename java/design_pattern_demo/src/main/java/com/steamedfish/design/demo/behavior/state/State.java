package com.steamedfish.design.demo.behavior.state;

/**
 * 〈状态模式〉<br>
 * 允许对象在内部状态改变时改变它的行为
 *
 * @author steamedfish
 * @create 2020/1/13
 * @since 1.0.0
 */
public interface State {

    /**
     * 投入
     */
    void insertQuarter();

    /**
     * 退回
     */
    void ejectQuarter();

    /**
     * 抽奖
     */
    void turnCrank();

    /**
     * 发放
     */
    void dispense();
}
