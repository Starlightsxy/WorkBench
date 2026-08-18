package com.work.bench.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 可以返回 token 的 VO
 * @author 洁心未眠
 * @Package com.work.bench.vo.user
 * @date 2026/8/10 17:06
 */
@Data
@AllArgsConstructor
public class LoginTokenVO {
    // 用于业务的accessToken
    private String accessToken;
    // 用于accessToken过期时，拿到refreshToken去刷新accessToken
    private String refreshToken;
}
