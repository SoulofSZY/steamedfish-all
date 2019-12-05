package com.steamedfish.fastjson.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.steamedfish.fastjson.demo.bean.Address;
import com.steamedfish.fastjson.demo.bean.Author;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/4
 * @since 1.0.0
 */
public class TestGenericConvertBean {


    public static void main(String[] args) {
        Author author1 = Author.builder()
                .name("张三").age(18)
                .model(Address.builder().city("深圳").district("南山").build())
                .build();
        Author author2 = Author.builder()
                .name("李四").age(18)
                .model(Address.builder().city("深圳").district("罗湖").build())
                .build();

        List<Author> list = new ArrayList<Author>();
        list.add(author1);
        list.add(author2);

        String authorListJSON = JSON.toJSONString(list);
        System.out.println(authorListJSON);

        List list1 = JSON.parseObject(authorListJSON, List.class);

        Type type = new TypeReference<List<Author<Address>>>(){}.getType();
        List<Author<Address>> list2 = JSON.parseObject(authorListJSON, type);

        String authorJSON = JSON.toJSONString(author1);
        Type type1 = new TypeReference<Author<Address>>(){}.getType();

        System.out.println("---------------");

    }

    private static void changeStr(String str) {

    }
}