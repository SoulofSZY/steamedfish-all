package com.steamedfish.mybatis.demo.fun;

import org.apache.ibatis.session.SqlSession;

/**
 * 〈命令〉
 *
 * @author steamedfish
 * @create 2019/12/4
 * @since 1.0.0
 */
public interface Command {

    void exec(SqlSession session, Object mapper);
}