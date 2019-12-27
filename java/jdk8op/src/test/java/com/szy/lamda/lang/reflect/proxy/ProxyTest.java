package com.szy.lamda.lang.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 〈动态代理demo〉
 *
 * @author sunzhengyu
 * @create 2019/10/16
 * @since 1.0.0
 */
public class ProxyTest {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        HelloHandler handler = new HelloHandler(hello);
        Hello helloProxy = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler);
        helloProxy.sayHello();
    }

}

interface Hello {
    void sayHello();
}

class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello!!");
    }
}

class HelloHandler implements InvocationHandler {
    private Object target;

    public HelloHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy invoke hello");
        Object invoke = method.invoke(target, args);
        return invoke;
    }
}