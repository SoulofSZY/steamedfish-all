package com.springboot.tk.demo.action;

import com.alibaba.fastjson.JSON;
import com.springboot.tk.demo.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/20
 * @since 1.0.0
 */
@Component
@Slf4j
public class TestAction implements ApplicationRunner {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(JSON.toJSONString(personMapper.selectAll()));
    }
}