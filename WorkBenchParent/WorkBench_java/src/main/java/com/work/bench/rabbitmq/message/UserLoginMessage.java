package com.work.bench.rabbitmq.message;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录日志
 *
 * @author 洁心未眠
 * @Package com.work.bench.rabbitmq.message
 * @date 2026/8/17 16:29
 */
@Data
@Builder
public class UserLoginMessage implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 登录状态 0失败，1成功
     */
    private Integer loginStatus;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 客户端信息
     */
    private String userAgent;


}
