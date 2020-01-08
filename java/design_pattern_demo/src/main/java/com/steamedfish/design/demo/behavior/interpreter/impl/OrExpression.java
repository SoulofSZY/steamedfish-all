package com.steamedfish.design.demo.behavior.interpreter.impl;

import com.steamedfish.design.demo.behavior.interpreter.Expression;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class OrExpression extends Expression {

    private Expression one;
    private Expression two;

    public OrExpression(Expression one, Expression two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean interpret(String str) {
        return one.interpret(str) || two.interpret(str);
    }
}