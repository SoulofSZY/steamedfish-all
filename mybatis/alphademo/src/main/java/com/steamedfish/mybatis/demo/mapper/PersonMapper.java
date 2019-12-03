package com.steamedfish.mybatis.demo.mapper;

import com.steamedfish.mybatis.demo.bean.Person;

import java.util.Map;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/3
 * @since 1.0.0
 */
public interface PersonMapper {

    /**
     * 根据id
     * @param id
     * @return
     */
    Map selectPerson(long id);

    /**
     * test1
     * @param id
     * @return
     */
    Map selectPerson1(long id);

    /**
     * 插入
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 插入 test1
     * @param person
     * @return
     */
    int insertPerson1(Person person);

    /**
     * 更新
     * @param person
     * @return
     */
    int updatePerson(Person person);


    /**
     * 删除
     * @param id
     * @return
     */
    int deletePerson(long id);



}