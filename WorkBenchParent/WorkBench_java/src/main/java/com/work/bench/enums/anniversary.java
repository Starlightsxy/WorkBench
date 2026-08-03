package com.work.bench.enums;

import lombok.Getter;

/**
 * 纪念日
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:14
 */
@Getter
public enum anniversary {

    // 纪念日类型
    ANNIVERSARY(1, "纪念日"),
    BIRTHDAY(2, "生日"),
    FESTIVAL(3, "节日"),

    // 是否每年重复
    REPEAT_TRUE(1, "是"),
    REPEAT_FALSE(2, "否");

    private final Integer code;
    private final String desc;

    anniversary(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
