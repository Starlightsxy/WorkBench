package com.work.bench.pojo.logo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 洁心未眠
 * @Package com.work.bench.pojo.logo
 * @date 2026/8/17 16:03
 */
@TableName("tb_login_log")
@Data
public class LoginLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String account;
    private String loginIp;
    private Long loginTime;
    // 登录状态 0 失败 1成功
    private Integer loginStatus;
    private String failReason;
    private String userAgent;
}
