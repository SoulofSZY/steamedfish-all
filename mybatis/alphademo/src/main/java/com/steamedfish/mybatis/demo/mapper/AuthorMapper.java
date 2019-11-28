package com.steamedfish.mybatis.demo.mapper;

import com.steamedfish.mybatis.demo.bean.Author;
import com.steamedfish.mybatis.demo.bean.Blog;
import org.apache.ibatis.annotations.Select;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
public interface AuthorMapper {
    /**
     * 根据id获取blog
     * @param id
     * @return
     */
    Author selectOne(long id);

    @Select("SELECT * FROM t_author WHERE id = #{id}")
    Author selectAuthor2(long id);


}