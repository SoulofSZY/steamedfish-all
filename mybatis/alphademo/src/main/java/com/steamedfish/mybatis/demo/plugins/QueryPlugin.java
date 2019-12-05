package com.steamedfish.mybatis.demo.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 〈插件〉
 *  MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
 *
 *     Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 *     ParameterHandler (getParameterObject, setParameters)
 *     ResultSetHandler (handleResultSets, handleOutputParameters)
 *     StatementHandler (prepare, parameterize, batch, update, query)
 * @author steamedfish
 * @create 2019/11/29
 * @since 1.0.0
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class QueryPlugin implements Interceptor {
    private Properties properties = new Properties();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}