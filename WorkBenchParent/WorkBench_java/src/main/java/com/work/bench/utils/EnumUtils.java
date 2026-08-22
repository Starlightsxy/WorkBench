package com.work.bench.utils;

import com.work.bench.common.BaseEnum;

/**
 * 枚举通用工具类
 * @author 洁心未眠
 * @Package com.work.bench.utils
 * @date 2026/8/14 19:01
 */
public final class EnumUtils {
    private EnumUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 根据 code 获取 desc（若找不到返回空字符串）
     */
    public static <T extends BaseEnum> String getDescByCode(Class<T> enumClass, Integer code) {
        if (code == null) {
            return "";
        }
        for (T item : enumClass.getEnumConstants()) {
            if (item.getCode().equals(code)) {
                return item.getDesc();
            }
        }
        return "";
    }

    /**
     *
     * @param enumClass
     * @param desc
     * @return
     * @param <T>
     */
    public static <T extends BaseEnum> Integer getCodeByDesc(Class<T> enumClass, String desc) {
        if (desc == null) {
            return 0;
        }
        for (T item : enumClass.getEnumConstants()) {
            if(item.getDesc().equals(desc)) {
                return item.getCode();
            }
        }
        return 0;
    }
    /**
     * 根据 code 获取枚举实例（若找不到返回 null）
     */
    public static <T extends BaseEnum> T getEnumByCode(Class<T> enumClass, Integer code) {
        if (code == null) {
            return null;
        }
        for (T item : enumClass.getEnumConstants()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
