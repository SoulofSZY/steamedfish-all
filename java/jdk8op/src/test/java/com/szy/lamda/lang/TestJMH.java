package com.szy.lamda.lang;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * 〈一句话功能简述〉<br>
 * 〈todo  基准测试demo〉
 *
 * @author sunzhengyu
 * @create 2019/9/4
 * @since 1.0.0
 */
public class TestJMH {


    @Benchmark
    public static String printString() {
        String result  = "";
        for (int i=0; i<10000; i++) {
            result += "hello";
        }
        return result;
    }


    public static void main(String[] args) {
        printString();
    }
}