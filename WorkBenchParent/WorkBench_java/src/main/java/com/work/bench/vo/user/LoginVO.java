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
public class LoginVO {
    private String token;
    private UserInfoVO userInfo;
}
