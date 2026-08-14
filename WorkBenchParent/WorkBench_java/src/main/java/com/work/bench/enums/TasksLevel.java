package com.work.bench.enums;

import com.work.bench.common.BaseEnum;
import lombok.Getter;

/**
 * 任务级别
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/14 18:52
 */
@Getter
public enum TasksLevel implements BaseEnum {
    LOW(1, "低"),
    NORMAL(2, "中"),
    HIGH(3, "高");

    private final Integer code;
    private final String desc;

    TasksLevel(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
