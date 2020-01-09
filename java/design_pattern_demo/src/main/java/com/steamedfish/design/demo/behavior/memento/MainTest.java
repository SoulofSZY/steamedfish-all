package com.steamedfish.design.demo.behavior.memento;

import com.steamedfish.design.demo.behavior.memento.impl.CalculatorImpl;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public class MainTest {


    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        calculator.setFirstNumber(10);
        calculator.setSecondNumber(12);
        System.out.println(calculator.getCalculationResult());
        System.out.println("--------------------");
        PreviousCalculationToCareTaker memento = calculator.backupLastCalculation();

        calculator.setSecondNumber(-10);
        System.out.println(calculator.getCalculationResult());
        System.out.println("---------------------");

        calculator.restorePreviousCalculation(memento);
        System.out.println(calculator.getCalculationResult());
        System.out.println("---------------------");
    }
}