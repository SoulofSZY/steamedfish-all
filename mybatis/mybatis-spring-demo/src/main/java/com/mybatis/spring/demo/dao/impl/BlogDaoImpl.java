package com.mybatis.spring.demo.dao.impl;

import com.mybatis.spring.demo.bean.Blog;
import com.mybatis.spring.demo.dao.BlogDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/11
 * @since 1.0.0
 */
@Repository
public class BlogDaoImpl implements BlogDao {

    private final SqlSession sqlSession;

    public BlogDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Blog getBlog(long id) {
        return sqlSession.selectOne("com.mybatis.spring.demo.mapper.BlogMapper.getBlog", id);
    }

    @Override
    public int insertBlogList(List<Blog> blogList) {
        int i = 0;
        for (; i<blogList.size(); i++) {
            sqlSession.insert("com.mybatis.spring.demo.mapper.BlogMapper.insertBlog", blogList.get(i));
        }
        return i;
    }
}