package com.work.bench.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录请求和响应的日志
 * @author 洁心未眠
 * @Package com.work.bench.annotation
 * @date 2026/8/13 23:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AroundLog {
}
