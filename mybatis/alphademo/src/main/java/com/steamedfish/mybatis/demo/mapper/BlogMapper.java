package com.steamedfish.mybatis.demo.mapper;

import com.steamedfish.mybatis.demo.bean.Blog;
import org.apache.ibatis.annotations.Select;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
public interface BlogMapper {
    /**
     * 根据id获取blog
     * @param id
     * @return
     */
    Blog selectOne(long id);

    @Select("SELECT * FROM t_blog WHERE id = #{id}")
    Blog selectBlog2(long id);
}