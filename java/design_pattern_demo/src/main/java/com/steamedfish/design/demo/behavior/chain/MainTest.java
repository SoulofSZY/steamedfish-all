package com.steamedfish.design.demo.behavior.chain;

import com.steamedfish.design.demo.behavior.chain.component.Request;
import com.steamedfish.design.demo.behavior.chain.component.RequestType;
import com.steamedfish.design.demo.behavior.chain.impl.ConcreteHandler1;
import com.steamedfish.design.demo.behavior.chain.impl.ConcreteHandler2;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1(null);
        Handler handler2 = new ConcreteHandler2(handler1);

        Request request1 = new Request(RequestType.TYPE1, "request1");
        handler2.handleRequest(request1);

        Request request2 = new Request(RequestType.TYPE2, "request2");
        handler2.handleRequest(request2);
    }
}