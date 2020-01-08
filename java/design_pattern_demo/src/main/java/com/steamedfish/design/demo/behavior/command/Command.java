package com.steamedfish.design.demo.behavior.command;

/**
 * 〈命令模式〉
 *  将命令封装成对象中，具有以下作用：
 *     使用命令来参数化其它对象
 *     将命令放入队列中进行排队
 *     将命令的操作记录到日志中
 *     支持可撤销的操作
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public interface Command {
    void execute();
}