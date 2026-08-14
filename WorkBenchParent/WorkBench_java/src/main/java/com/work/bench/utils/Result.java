package com.work.bench.utils;

import com.work.bench.enums.BusinessStatus;
import com.work.bench.enums.BusinessType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Result<T> {
    private Integer code;    // 状态码，如 0，1
    private String message;  // 提示信息
    private T data;          // 具体数据


    // -------- 成功响应 (链式调用) --------
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(BusinessStatus.SUCCESS.getCode());
        result.setMessage("操作成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(BusinessStatus.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // -------- 失败响应 --------

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(BusinessStatus.FAIL.getCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}