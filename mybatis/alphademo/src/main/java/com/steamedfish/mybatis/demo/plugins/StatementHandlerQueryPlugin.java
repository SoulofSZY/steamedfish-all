package com.steamedfish.mybatis.demo.plugins;

import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.text.MessageFormat;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/5
 * @since 1.0.0
 */
@Intercepts({@Signature(
        type= StatementHandler.class,
        method = "query",
        args = {Statement.class, ResultHandler.class})})
public class StatementHandlerQueryPlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DruidPooledPreparedStatement statement = (DruidPooledPreparedStatement)invocation.getArgs()[0];
        System.out.println("-----------");
        System.out.println(MessageFormat.format("sql exec:{0}", statement.getSql()));
        System.out.println("-----------");
        Object result = invocation.proceed();
        return result;
    }
}