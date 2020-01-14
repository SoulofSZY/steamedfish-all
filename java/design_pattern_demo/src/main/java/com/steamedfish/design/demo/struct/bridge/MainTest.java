package com.steamedfish.design.demo.struct.bridge;

import com.steamedfish.design.demo.struct.bridge.impl.ConcreteController1;
import com.steamedfish.design.demo.struct.bridge.impl.ConcreteController2;
import com.steamedfish.design.demo.struct.bridge.impl.RCA;
import com.steamedfish.design.demo.struct.bridge.impl.Sony;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        RemoteControl controller1 = new ConcreteController1(new Sony());
        controller1.on();
        controller1.tuneChannel();

        RemoteControl controller2 = new ConcreteController2(new RCA());
        controller2.on();
        controller2.off();
    }
}