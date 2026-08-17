package com.work.bench.enums;

import com.work.bench.common.BaseEnum;
import lombok.Getter;

/**
 * 登录日志状态
 *
 * @author 洁心未眠
 * @Package com.work.bench.enums
 * @date 2026/8/17 16:47
 */
@Getter
public enum LoginLogStatus implements BaseEnum {
    FAIL(0, "失败"),
    SUCCESS(1, "成功");

    private final Integer code;
    private final String desc;

    // 构造方法私有（Lombok 不会生成构造方法，需要手动写）
    LoginLogStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
