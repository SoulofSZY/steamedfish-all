package com.steamedfish.design.demo.behavior.memento;

/**
 * 〈Memento Interface to Originator〉<br>
 *  This interface allows the originator to restore its state
 * @author steamedfish
 * @create 2020/1/9
 * @since 1.0.0
 */
public interface PreviousCalculationToOriginator {

    int getFirstNum();
    int getSecondNum();
}
