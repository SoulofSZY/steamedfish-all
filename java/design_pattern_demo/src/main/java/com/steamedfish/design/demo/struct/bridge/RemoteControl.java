package com.steamedfish.design.demo.struct.bridge;

/**
 * 〈桥接模式〉
 *  将抽象与实现分离开来，使它们可以独立变化。
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public abstract class RemoteControl {

    protected TV tv;

    public RemoteControl(TV tv) {
        this.tv = tv;
    }

    protected abstract void on();

    protected abstract void off();

    protected abstract void tuneChannel();

}