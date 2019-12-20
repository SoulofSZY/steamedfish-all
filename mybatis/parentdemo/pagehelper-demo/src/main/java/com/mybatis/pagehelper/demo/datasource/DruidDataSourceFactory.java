package com.mybatis.pagehelper.demo.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.type.Alias;

/**
 * 〈自定义数据源〉
 *
 * @author steamedfish
 * @create 2019/11/29
 * @since 1.0.0
 */
@Alias("DruidDataSourceFactory")
public class DruidDataSourceFactory extends PooledDataSourceFactory {

    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}