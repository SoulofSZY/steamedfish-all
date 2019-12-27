package spring.test;

import com.spring.mybatis.plus.demo.QuickStartApplication;
import com.spring.mybatis.plus.demo.bean.User;
import com.spring.mybatis.plus.demo.loop.TestA;
import com.spring.mybatis.plus.demo.loop.TestB;
import com.spring.mybatis.plus.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2019/12/20
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuickStartApplication.class})
public class SampleTest {

    @Autowired
    TestA testA;
    @Autowired
    TestB testB;

    @Test
    public void testSelect() {
        System.out.println(testA.toString());
        System.out.println(testB.toString());
    }
}