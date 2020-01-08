package com.steamedfish.design.demo.behavior.chain;

import com.steamedfish.design.demo.behavior.chain.component.Request;

/**
 * 〈责任链模式〉
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止。
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public abstract class Handler {

    protected Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Request request);
}