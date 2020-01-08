package com.steamedfish.design.demo.behavior.interpreter.impl;

import com.steamedfish.design.demo.behavior.interpreter.Expression;

import java.util.StringTokenizer;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class TerminalExpression extends Expression {

    private String literal;

    public TerminalExpression(String literal) {
        this.literal = literal;
    }

    @Override
    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String test = st.nextToken();
            if (test.equals(this.literal)) {
                return true;
            }
        }
        return false;
    }
}