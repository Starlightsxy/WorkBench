package com.work.bench.enums;

import lombok.Getter;

/**
 * 纪念日是否每年重复
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/13 18:58
 */
@Getter
public enum AnniversaryRepeatTypeEnum {
    REPEAT_TRUE(1, "是"),
    REPEAT_FALSE(2, "否");

    private final Integer code;
    private final String desc;

    AnniversaryRepeatTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(Integer code) {
        if (code != null) {
            for (AnniversaryRepeatTypeEnum item : AnniversaryRepeatTypeEnum.values()) {
                if (item.code.equals(code)) {
                    return item.desc;
                }
            }
        }
        return "";

    }
}
