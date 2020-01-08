package com.steamedfish.design.demo.behavior.command;

import com.steamedfish.design.demo.behavior.command.impl.LightOffCommand;
import com.steamedfish.design.demo.behavior.command.impl.LightOnCommand;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Light light = new Light();

        Command onCommand = new LightOnCommand(light);
        invoker.setOnCommand(onCommand, 0);

        Command offCommand = new LightOffCommand(light);
        invoker.setOffCommand(offCommand, 0);

        invoker.onBtnPushed(0);
        invoker.offBtnPushed(0);
    }
}