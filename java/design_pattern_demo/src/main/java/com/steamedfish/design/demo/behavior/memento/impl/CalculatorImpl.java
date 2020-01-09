package com.steamedfish.design.demo.behavior.memento.impl;

import com.steamedfish.design.demo.behavior.memento.Calculator;
import com.steamedfish.design.demo.behavior.memento.PreviousCalculationImp;
import com.steamedfish.design.demo.behavior.memento.PreviousCalculationToCareTaker;
import com.steamedfish.design.demo.behavior.memento.PreviousCalculationToOriginator;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class CalculatorImpl implements Calculator {

    private int firstNum;
    private int secondNum;


    @Override
    public PreviousCalculationToCareTaker backupLastCalculation() {
        return new PreviousCalculationImp(this.firstNum, this.secondNum);
    }

    @Override
    public void restorePreviousCalculation(PreviousCalculationToCareTaker memento) {
        this.firstNum = ((PreviousCalculationToOriginator) memento).getFirstNum();
        this.secondNum = ((PreviousCalculationToOriginator) memento).getSecondNum();
    }

    @Override
    public int getCalculationResult() {
        return this.firstNum + this.secondNum;
    }

    @Override
    public void setFirstNumber(int firstNumber) {
        this.firstNum = firstNumber;
    }

    @Override
    public void setSecondNumber(int secondNumber) {
        this.secondNum = secondNumber;
    }
}