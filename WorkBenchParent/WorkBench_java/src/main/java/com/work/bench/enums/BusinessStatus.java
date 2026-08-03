package com.work.bench.enums;

import lombok.Getter;

/**
 * 业务状态 成功 失败
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:19
 */
@Getter
public enum BusinessStatus {

    FAIL(0, "失败"),
    SUCCESS(1, "成功");

    private final Integer code;
    private final String desc;

    BusinessStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
