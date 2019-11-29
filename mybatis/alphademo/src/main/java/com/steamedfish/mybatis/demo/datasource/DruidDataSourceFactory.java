package com.steamedfish.mybatis.demo.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

/**
 * 〈自定义数据源〉
 *
 * @author steamedfish
 * @create 2019/11/29
 * @since 1.0.0
 */
public class DruidDataSourceFactory extends PooledDataSourceFactory {

    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}