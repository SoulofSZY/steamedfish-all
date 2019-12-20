package com.mybatis.spring.demo.dao;

import com.mybatis.spring.demo.bean.Blog;

import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/11
 * @since 1.0.0
 */
public interface BlogDao {

    Blog getBlog(long id);

    int insertBlogList(List<Blog> blogList);
}