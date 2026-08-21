package com.work.bench.dto.user;

import lombok.Data;

/**
 * refreshTokenDTO 用于前端刷新token时，传递的dto
 * @author 洁心未眠
 * @Package com.work.bench.dto.User
 * @date 2026/8/18 17:43
 */
@Data
public class RefreshTokenDTO {
    private String refreshToken;
}
