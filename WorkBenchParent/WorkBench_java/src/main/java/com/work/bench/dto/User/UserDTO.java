package com.work.bench.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表 dto
 *
 * @author 洁心未眠
 * @Package com.work.bench.dto
 * @date 2026/8/3 15:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    // 用户主键id
    private Integer id;
    // 用户名
    private String userName;
    // 昵称
    private String nickName;
    // 密码
    private String password;
    // 头像
    private String avatar;
    // 邮箱
    private String email;
    // 电话
    private String phone;
    // 性别 (0未知 1男 2女)'
    private Integer gender;
    // 生日
    private Long birthday;
    // 个性签名
    private String signature;
    // 主题
    private String theme;
    // 创建时间
    private Long createTime;
    // 修改时间
    private Long updateTime;
    // '逻辑删除 0 不删除  1 删除'
    private Integer deleted;
}
