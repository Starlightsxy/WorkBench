package com.work.bench.enums;

import lombok.Getter;

/**
 * 财务计划表 年度月度
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/22 00:02
 */
@Getter
public enum BillPlanType {

    YEAR(1, "月度"),

    MONTH(2, "年度");

    private final Integer code;
    private final String desc;

    BillPlanType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
