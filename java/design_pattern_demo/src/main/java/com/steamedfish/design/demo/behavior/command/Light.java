package com.steamedfish.design.demo.behavior.command;

/**
 * 〈Receiver：命令接收者，也就是命令真正的执行者〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class Light {

    public void on() {
        System.out.println("Light is on!");
    }

    public void off() {
        System.out.println("Light is off!");
    }
}