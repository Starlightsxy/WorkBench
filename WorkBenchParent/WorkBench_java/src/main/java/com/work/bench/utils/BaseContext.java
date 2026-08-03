package com.work.bench.utils;

/**
 * 当前线程上下文对象，专门存储用户信息的
 *
 * @author 洁心未眠
 * @Package com.work.bench.utils
 * @date 2026/8/3 16:41
 */
public class BaseContext {
    //  设置一个ThreadLocal对象，用于存储当前线程的用户id
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    //  设置一个方法，用于存储当前线程的用户id
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    //  设置一个方法，用于获取当前线程的用户id
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    //  设置一个方法，用于移除当前线程的用户id
    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
