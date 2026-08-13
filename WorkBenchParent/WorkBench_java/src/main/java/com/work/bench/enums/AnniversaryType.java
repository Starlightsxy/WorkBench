package com.work.bench.enums;

import lombok.Getter;

/**
 * 纪念日
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:14
 */
@Getter
public enum AnniversaryType {

    // 纪念日类型
    ANNIVERSARY(1, "纪念日"),
    BIRTHDAY(2, "生日"),
    FESTIVAL(3, "倒计时日");

    private final Integer code;
    private final String desc;

    AnniversaryType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDescByCode(Integer code) {
        if (code != null) {
            for (AnniversaryType item : AnniversaryType.values()) {
                if (item.getCode().equals(code)) {
                    return item.getDesc();
                }
            }
        }
        return "";

    }

}
