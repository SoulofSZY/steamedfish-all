package com.mybatis.spring.demo.mapper;

import com.mybatis.spring.demo.bean.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/11
 * @since 1.0.0
 */
public interface BlogMapper {

    @Select("select * from t_blog where id=#{id}")
    Blog getBlog(long id);

    @Insert("insert into t_blog values(#{id}, #{title})")
    int insertBlog(Blog blog);
}