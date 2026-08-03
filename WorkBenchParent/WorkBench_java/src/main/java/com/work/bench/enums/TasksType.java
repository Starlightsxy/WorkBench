package com.work.bench.enums;

import lombok.Getter;

/**
 * 待办任务枚举
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 19:32
 */
@Getter
public enum TasksType {
    PENDING(0, "待办"),
    IN_PROGRESS(1, "进行中"),
    COMPLETED(2, "已完成"),
    CANCELLED(3, "已取消"),
    LOW(1, "低"),
    NORMAL(2, "中"),
    HIGH(3, "高");

    private final Integer code;
    private final String desc;

    TasksType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
