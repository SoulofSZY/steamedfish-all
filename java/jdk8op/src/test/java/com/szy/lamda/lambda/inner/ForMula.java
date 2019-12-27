/**
 * Copyright (C), 2014-2019, 深圳兔展智能科技有限公司
 * FileName: ForMula
 * Author:   EDZ
 * Date:     2019/7/10 15:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.szy.lamda.lambda.inner;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author EDZ
 * @create 2019/7/10
 * @since 1.0.0
 */
public interface ForMula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
