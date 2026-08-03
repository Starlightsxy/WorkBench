package com.work.bench.enums;

import lombok.Getter;

/**
 * 性别 0 未知  1 男 2 女
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:06
 */
@Getter
public enum GenderType {


    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final Integer code;
    private final String desc;

    // 构造方法私有（Lombok 不会生成构造方法，需要手动写）
    GenderType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
