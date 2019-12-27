package com.szy.lamda.lambda.bean;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/10
 * @since 1.0.0
 */
@Data
public class Person {

    private String name;
    private int age;

    public void printName(){
        System.out.println(this.name);
    }
}