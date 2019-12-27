package com.szy.lamda.common;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/19
 * @since 1.0.0
 */
public class TestParallel {


    @Test
    public void test01() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        IntStream.range(0, 100000).parallel().forEach(i -> {
            list.add(i);
        });
        Map<Integer, Integer> resultMap = new HashMap<>();
        list.stream().forEach(i -> {
            if (resultMap.containsKey(i)) {
                resultMap.put(i, resultMap.get(i) + 1);
            }
            resultMap.put(i, 1);
        });

        resultMap.forEach((key, val) -> {
            if (val > 1) {
                System.out.println(key);
            }
        });

        System.out.println("----------over----------------");
    }

    @Test
    public void test(){
        System.out.println(new Date().getTime());
    }
}