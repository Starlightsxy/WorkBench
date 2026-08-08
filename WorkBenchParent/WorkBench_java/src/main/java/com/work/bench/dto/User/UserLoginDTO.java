package com.work.bench.dto.User;

import lombok.Data;

/**
 * 统一接收登录的DTO
 * @author 洁心未眠
 * @Package com.work.bench.dto.User
 * @date 2026/8/8 19:42
 */
@Data
public class UserLoginDTO {
    private String account;
    private String password;
}
