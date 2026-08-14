package com.work.bench.enums;

import com.work.bench.common.BaseEnum;
import lombok.Getter;

/**
 * 账单类型 1 收入 2 支出
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:08
 */
@Getter
public enum BillType implements BaseEnum {

    INCOME(1, "收入"),

    EXPENSE(2, "支出");

    private final Integer code;
    private final String desc;

    BillType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
