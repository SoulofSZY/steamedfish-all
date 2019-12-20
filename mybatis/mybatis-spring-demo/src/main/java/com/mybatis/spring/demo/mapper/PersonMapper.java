package com.mybatis.spring.demo.mapper;

import com.mybatis.spring.demo.bean.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/3
 * @since 1.0.0
 */
public interface PersonMapper {

    @Select("select * from t_person where ${column} = #{value}")
    List<Person> selectByColumn(@Param("column") String column, @Param("value") String value);

    @Insert("insert into t_person(id, name, age, gender)values(#{id}, #{name}, #{age}, #{gender})")
    int inserPerson(Person person);
}