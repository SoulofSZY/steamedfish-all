package com.steamedfish.design.demo.behavior.interpreter;

/**
 * 〈解释器模式〉
 * 为语言创建解释器，通常由语言的语法和语法分析来定义。
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public abstract class Expression {
    public abstract boolean interpret(String str);
}