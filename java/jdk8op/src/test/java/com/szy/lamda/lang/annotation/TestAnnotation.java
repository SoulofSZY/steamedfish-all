package com.szy.lamda.lang.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈测试注解〉
 *
 * @author sunzhengyu
 * @create 2019/9/19
 * @since 1.0.0
 */
@Slf4j
public class TestAnnotation {

    private static final String data = "\"b46555b190724234238133497.wav\"|\"good afternoon\"|\"2019-07-24 23:42:39\"\"b46555b190929024344106254.wav\"|\"test1\"|\"2019-09-29 02:43:45\"\"b46555b190929024410187416.wav\"|\"test2\"|\"2019-09-29 02:44:10\"";

    @Test
    public void test() {
        String regEx = "/(?:\"(?:.)*\"\\|){2}\"(?:.)*(\"){1}/";

        boolean matches = "\"\"|\"\"|\"\"".matches(regEx);

        log.info("000");
//        Pattern pattern = Pattern.compile(regEx);
//        Matcher matcher = pattern.matcher(data);
//        boolean rs = matcher.matches();
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }

    }
}