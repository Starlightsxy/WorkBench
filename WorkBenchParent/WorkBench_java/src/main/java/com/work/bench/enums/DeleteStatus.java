package com.work.bench.enums;

import com.work.bench.common.BaseEnum;
import lombok.Getter;

/**
 * 逻辑删除状态 0 未删除 1 删除
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/3 17:05
 */
@Getter
public enum DeleteStatus implements BaseEnum {

    UNDELETED(0, "未删除"), // 未删除

    DELETED(1, "已删除"); // 已删除

    private final Integer code;
    private final String desc;

    DeleteStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
