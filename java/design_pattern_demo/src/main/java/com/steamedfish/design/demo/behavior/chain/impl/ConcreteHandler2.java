package com.steamedfish.design.demo.behavior.chain.impl;

import com.steamedfish.design.demo.behavior.chain.Handler;
import com.steamedfish.design.demo.behavior.chain.component.Request;
import com.steamedfish.design.demo.behavior.chain.component.RequestType;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            System.out.println(request.getName() + " is handle by ConcreteHandler2");
            return;
        }
        if (successor != null) {
            successor.handleRequest(request);
        }
    }
}