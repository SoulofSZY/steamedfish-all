package com.steamedfish.design.demo.behavior.command.impl;

import com.steamedfish.design.demo.behavior.command.Command;
import com.steamedfish.design.demo.behavior.command.Light;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }
}