package com.steamedfish.design.demo.behavior.memento;

/**
 * 〈备忘录模式〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class PreviousCalculationImp implements PreviousCalculationToOriginator, PreviousCalculationToCareTaker {

    private int firstNum;
    private int secondNum;

    public PreviousCalculationImp(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    @Override
    public int getFirstNum() {
        return this.firstNum;
    }

    @Override
    public int getSecondNum() {
        return this.secondNum;
    }
}