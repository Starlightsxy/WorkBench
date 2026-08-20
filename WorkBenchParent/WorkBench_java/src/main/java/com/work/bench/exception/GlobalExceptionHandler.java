package com.work.bench.exception;

import com.work.bench.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author 洁心未眠
 * @Package com.work.bench.exception
 * @date 2026/8/3 16:40
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param e e
     * @return Result
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        return Result.error(e.getMessage());
    }

    /**
     * 处理数据库异常（如 SQL 语法错误、数据完整性冲突等）
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 返回 500 状态码
    public Result<String> handleDataAccessException(DataAccessException e) {
        log.error("数据库异常：", e); // 打印完整堆栈
        return Result.error("数据操作失败，请稍后重试");
    }
}
