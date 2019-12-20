package com.mybatis.pagehelper.demo.mapper;

import com.mybatis.pagehelper.demo.bean.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.Alias;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 〈测试〉
 *
 * @author steamedfish
 * @create 2019/12/3
 * @since 1.0.0
 */
@Alias("PersonMapper")
public interface PersonMapper extends Mapper<Person> {


    @Select("select * from t_person where ${column} = #{value}")
    List<Person> selectByColumn(@Param("column") String column, @Param("value") String value);

    @Select("select * from t_person")
    List<Person> selectPager(RowBounds rowBounds);
}