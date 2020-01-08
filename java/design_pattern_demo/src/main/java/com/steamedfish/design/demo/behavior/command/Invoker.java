package com.steamedfish.design.demo.behavior.command;

/**
 * 〈Invoker：通过它来调用命令〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class Invoker {

    private Command[] onCommands;
    private Command[] offCommands;
    private final int slotNum = 7;

    public Invoker() {
        this.onCommands = new Command[slotNum];
        this.offCommands = new Command[slotNum];
    }

    public void setOnCommand(Command command, int slot) {
        this.onCommands[slot] = command;
    }

    public void setOffCommand(Command command, int slot) {
        this.offCommands[slot] = command;
    }

    public void onBtnPushed(int slot) {
        this.onCommands[slot].execute();
    }

    public void offBtnPushed(int slot) {
        this.offCommands[slot].execute();
    }
}