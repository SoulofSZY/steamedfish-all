/**
 * Copyright (C), 2014-2019, 深圳兔展智能科技有限公司
 * FileName: LambdaAlpha
 * Author:   EDZ
 * Date:     2019/6/6 19:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szy.lamda;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 * 〈jdk8 lambda〉
 *
 * @author EDZ
 * @create 2019/6/6
 * @since 1.0.0
 */
public class LambdaAlpha {

    @Test
    public void test4Predicate() {
        int portNum = 1000;
        Runnable run = () -> System.out.println(portNum);
        //portNum = 2000;
        run.run();
    }
}