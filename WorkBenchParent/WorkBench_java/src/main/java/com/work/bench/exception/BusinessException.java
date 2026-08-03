package com.work.bench.exception;

/**
 * 自定义业务异常
 *
 * @author 洁心未眠
 * @Package com.work.bench.exception
 * @date 2026/8/3 16:39
 */

public class BusinessException extends RuntimeException {
    /**
     * 传入自定义异常
     * @param message 异常消息
     */
    public BusinessException(String message) {
        super(message);
    }
}
