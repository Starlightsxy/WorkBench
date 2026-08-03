package com.work.bench.enums;

import lombok.Getter;

/**
 * 业务类型 增删改查 导出 导入
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:18
 */
@Getter
public enum BusinessType {
    INSERT(1, "新增"),
    DELETE(2, "删除"),
    UPDATE(3, "修改"),
    SELECT(4, "查询"),
    EXPORT(5, "导出"),
    IMPORT(6, "导入");

    private final Integer code;
    private final String desc;

    BusinessType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
