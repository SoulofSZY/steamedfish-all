package com.steamedfish.design.demo.struct.facade;

/**
 * 〈外观模式〉
 * 提供了一个统一的接口，用来访问子系统中的一群接口，从而让子系统更容易使用。
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class Facade {

    private SubSystem subSystem = new SubSystem();

    public void watchMovie() {
        subSystem.turnOnTV();
        subSystem.setCD("a movie");
        subSystem.startWatching();
    }

}