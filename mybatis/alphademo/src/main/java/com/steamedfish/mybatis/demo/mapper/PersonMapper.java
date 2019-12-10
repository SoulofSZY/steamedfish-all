package com.steamedfish.mybatis.demo.mapper;

import com.steamedfish.mybatis.demo.bean.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
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
     *
     * @param id
     * @return
     */
    Map selectPerson(long id);

    /**
     * test1
     *
     * @param id
     * @return
     */
    Map selectPerson1(long id);

    Person selectPerson2(long id);

    /**
     * 插入
     *
     * @param person
     * @return
     */
    int insertPerson(Person person);

    /**
     * 插入 test1
     *
     * @param person
     * @return
     */
    int insertPerson1(Person person);

    int insertPerson2(Person person);

    int insertPerson3(Person person);

    int insertPersonList(List<Person> list);

    /**
     * 更新
     *
     * @param person
     * @return
     */
    int updatePerson(Person person);

    int updatePerson1(Person person);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deletePerson(long id);

    int deletePerson1(long id);

    /**
     * 测试 sql 可重用SQL代码段
     */

    List<Map> selectPersons();

    List<Map> selectDynamicSQL();

    @Select("select * from t_person where ${column} = #{value}")
    List<Person> selectByColumn(@Param("column") String column, @Param("value") String value);

}