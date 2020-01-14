package com.steamedfish.design.demo.behavior.visitor;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public interface Element {

    void accept(Visitor visitor);
}
