package com.work.bench.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解，用于在方法执行之前记录 log
 * @author 洁心未眠
 * @Package com.work.bench.annotation
 * @date 2026/8/13 23:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeLog {
}
