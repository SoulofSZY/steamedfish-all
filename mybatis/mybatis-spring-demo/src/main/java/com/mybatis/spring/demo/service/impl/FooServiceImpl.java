package com.mybatis.spring.demo.service.impl;

import com.mybatis.spring.demo.bean.Person;
import com.mybatis.spring.demo.mapper.PersonMapper;
import com.mybatis.spring.demo.service.FooService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/10
 * @since 1.0.0
 */
@Service
public class FooServiceImpl implements FooService {

    private PersonMapper personMapper;

    public FooServiceImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public List<Person> findPeronByName(String name) {
        return personMapper.selectByColumn("name", name);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertPerson(Person person) {
        int result = personMapper.inserPerson(person);
        int err = 1/0;
        return result;
    }
}