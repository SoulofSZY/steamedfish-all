package com.steamedfish.design.demo.behavior.memento;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public interface Calculator {
    /**
     * Create Memento
     */
    PreviousCalculationToCareTaker backupLastCalculation();

    /**
     * setMemento
     *
     * @param memento
     */
    void restorePreviousCalculation(PreviousCalculationToCareTaker memento);

    int getCalculationResult();

    void setFirstNumber(int firstNumber);

    void setSecondNumber(int secondNumber);
}