package com.mybatis.spring.demo.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatis.spring.demo.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/10
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.mybatis.spring.demo.service")
public class BaseConfiguration {

    @Bean
    public DataSource dataSource() throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();
        Properties properties = new Properties();
        properties.load(Resources.getResourceAsReader("jdbc.properties"));
        druidDataSource.configFromPropety(properties);
        return druidDataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        ClassPathResource resource = new ClassPathResource("mapper/PersonMapper.xml");
        factoryBean.setMapperLocations(resource);
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public PersonMapper personMapper() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate.getMapper(PersonMapper.class);
    }
}