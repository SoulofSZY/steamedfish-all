package com.steamedfish.mybatis.demo.enums;

import lombok.Getter;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈性别〉
 *
 * @author steamedfish
 * @create 2019/11/29
 * @since 1.0.0
 */
@Alias("GenderEnum")
@Getter
public enum GenderEnum{
    MALE(1, "男"),
    FEMALE(2, "女");
    private int code;
    private String remark;
    private static Map<Integer,GenderEnum> codeLookup = new HashMap<>();

    static {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            codeLookup.put(genderEnum.code, genderEnum);
        }
    }

    GenderEnum(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    /**
     * @param code
     * @return
     */
    public static GenderEnum forCode(int code) {
       return codeLookup.get(code);
    }

}