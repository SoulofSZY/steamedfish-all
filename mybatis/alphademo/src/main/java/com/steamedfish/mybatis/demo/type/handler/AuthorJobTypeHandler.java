package com.steamedfish.mybatis.demo.type.handler;

import com.alibaba.fastjson.JSON;
import com.steamedfish.mybatis.demo.bean.Job;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 〈扩展点1 类型〉
 *
 * @author steamedfish
 * @create 2019/11/28
 * @since 1.0.0
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
//@MappedTypes({Job.class})
public class AuthorJobTypeHandler extends BaseTypeHandler<Job> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Job job, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(job));
    }

    @Override
    public Job getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JSON.parseObject(resultSet.getString(s), Job.class);
    }

    @Override
    public Job getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JSON.parseObject(resultSet.getString(i), Job.class);
    }

    @Override
    public Job getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSON.parseObject(callableStatement.getString(i), Job.class);
    }
}