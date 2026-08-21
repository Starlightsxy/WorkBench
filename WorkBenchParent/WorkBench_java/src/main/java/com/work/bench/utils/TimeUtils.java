package com.work.bench.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * 时间工具类
 *
 * @author 洁心未眠
 * @Package com.work.bench.utils
 * @date 2026/8/22 01:17
 */
@Component
public final class TimeUtils {
    // 统一时间戳
    public static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    // 直接返回时间开始和时间结束之间相差一个月之间的范围值
    public record TimeRange(Long start, Long end) {
    }

    /**
     * 获取 Java 程序运行环境的默认时区。
     * MySQL 中保存的是 Unix 时间戳，Unix 时间戳本身没有时区。
     * 在进行时间戳与日期时间之间的转换，以及计算本月、本年等时间范围时，需要明确指定时区。
     * 获取当前月初和下月月初之间的范围
     *
     * @return
     */

    public static TimeRange getCurrentMonthRange() {
        LocalDate now = LocalDate.now();
        // 开始时间
        long start = now
                .withDayOfMonth(1)
                .atStartOfDay(ZONE_ID)
                .toEpochSecond();

        // 结束时间
        long end = now
                .plusMonths(1)
                .withDayOfMonth(1)
                .atStartOfDay(ZONE_ID)
                .toEpochSecond();

        return new TimeRange(start, end);
    }

    /**
     * 获取当年1月1日和次年1月1日之间范围
     *
     * @return
     */
    public static TimeRange getCurrentYearRange() {
        LocalDate now = LocalDate.now();
        // 开始时间
        long start = now
                .withMonth(1)
                .withDayOfMonth(1)
                .atStartOfDay(ZONE_ID)
                .toEpochSecond();

        // 结束时间
        long end = now
                .plusYears(1)
                .withMonth(1)
                .withDayOfMonth(1)
                .atStartOfDay(ZONE_ID)
                .toEpochSecond();

        return new TimeRange(start, end);
    }
}
