package com.steamedfish.design.demo.behavior.interpreter;

import com.steamedfish.design.demo.behavior.interpreter.impl.AndExpression;
import com.steamedfish.design.demo.behavior.interpreter.impl.OrExpression;
import com.steamedfish.design.demo.behavior.interpreter.impl.TerminalExpression;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {


    /**
     * 构建语法树  D and (A or (B or C))
     *
     * @return
     */
    public static Expression buildInterpreterTree() {
        // terminal
        Expression terminalA = new TerminalExpression("A");
        Expression terminalB = new TerminalExpression("B");
        Expression terminalC = new TerminalExpression("C");
        Expression terminalD = new TerminalExpression("D");

        // 构建语法树
        // B OR C
        Expression alternative1 = new OrExpression(terminalB, terminalC);
        // A or (B or C)
        Expression alternative2 = new OrExpression(terminalA, alternative1);
        // D and (A or (B or C))
        return new AndExpression(terminalD, alternative2);
    }

    public static void main(String[] args) {
        Expression expression = buildInterpreterTree();

        String context = "D B";
        System.out.println(expression.interpret(context));

        String context2 = "D D";
        System.out.println(expression.interpret(context2));
    }
}