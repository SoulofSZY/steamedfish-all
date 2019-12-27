package com.szy.lambda;

import java.util.ArrayList;

/**
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/11/11
 * @since 1.0.0
 */

public class TestGenericity {

    public void test() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}