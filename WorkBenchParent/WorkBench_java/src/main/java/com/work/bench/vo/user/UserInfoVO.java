package com.work.bench.vo.user;

import lombok.Builder;
import lombok.Data;

/**
 * 用户信息展示
 *
 * @author 洁心未眠
 * @Package com.work.bench.vo.user
 * @date 2026/8/8 20:25
 */
@Data
@Builder
public class UserInfoVO {
    // 用户名
    private String userName;
    // 昵称
    private String nickName;
    // 头像
    private String avatar;
    // 邮箱
    private String email;
    // 电话
    private String phone;
    // 性别 (0未知 1男 2女)'
//    private Integer gender;
    // 性别字段
    private String gender;
    // 生日
    private Long birthday;
    // 个性签名
    private String signature;
    // 主题
    private String theme;
}
