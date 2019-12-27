package com.szy.lamda.lang;

import org.junit.Test;

/**
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/11/4
 * @since 1.0.0
 */
public class TestException {


    @Test
    public void test01() {
        try {
            System.out.println("1..");
            throw new RuntimeException("try block e");
        } catch (RuntimeException e) {
            System.out.println("2..");
            throw new RuntimeException("catch block e");
        } finally {
            System.out.println("3..");
            throw new RuntimeException("finally block e");
        }
    }
}