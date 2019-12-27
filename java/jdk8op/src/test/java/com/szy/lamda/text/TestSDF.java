package com.szy.lamda.text;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/11/21
 * @since 1.0.0
 */
@Slf4j
public class TestSDF {

    @Test
    public void testParse() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2019-12-12 23:12:11");

        System.out.println("111111111111111");
        //log.info("11111111111");
    }
}