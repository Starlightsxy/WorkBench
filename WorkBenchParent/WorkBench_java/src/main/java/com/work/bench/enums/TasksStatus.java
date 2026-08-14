package com.work.bench.enums;

import com.work.bench.common.BaseEnum;
import lombok.Getter;

/**
 * 任务状态
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 19:32
 */
@Getter
public enum TasksStatus implements BaseEnum {
    IN_PROGRESS(1, "进行中"), // 默认
    COMPLETED(2, "已完成"),
    CANCELLED(3, "已取消");


    private final Integer code;
    private final String desc;

    TasksStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
