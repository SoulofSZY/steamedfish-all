package com.steamedfish.mybatis.demo;

import com.steamedfish.mybatis.demo.bean.Author;
import com.steamedfish.mybatis.demo.bean.Blog;
import com.steamedfish.mybatis.demo.mapper.AuthorMapper;
import com.steamedfish.mybatis.demo.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 〈SqlSessionFactory〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
public class TestSqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;


    private static void fromXML() throws IOException {
        String resource = "mybatis/conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "dev-druid");
    }

    private void fromConfiguration() {
        DataSource dataSource = dataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("dev-druid", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 既加载类 也加载xml
        configuration.addMappers("com.steamedfish.mybatis.demo.mapper");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    private static Object selectOne(Class mapperClazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 一个语句既可以通过 XML 定义，也可以通过注解定义
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            val mapper = sqlSession.getMapper(mapperClazz);
            Method selectOneMethod = mapperClazz.getMethod("selectOne", long.class);
            return selectOneMethod.invoke(mapper, 1L);
        }
    }

    private static int updateOne(Class mapperClazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            val mapper = sqlSession.getMapper(mapperClazz);
            Method updateOneMethod = mapperClazz.getMethod("updateOne", long.class, String.class);
            return (int)updateOneMethod.invoke(mapper,1L, "张三");
        }
    }


    // 数据源
    private static DataSource dataSource() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        fromXML();
        System.out.println(selectOne(BlogMapper.class));
        System.out.println(selectOne(AuthorMapper.class));

 //       System.out.println(updateOne(BlogMapper.class));
    }
}