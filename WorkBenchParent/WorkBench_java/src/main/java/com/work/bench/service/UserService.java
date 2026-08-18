package com.work.bench.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.bench.dto.User.RefreshTokenDTO;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.pojo.User;
import com.work.bench.vo.user.LoginTokenVO;
import com.work.bench.vo.user.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录方法
     * @param userLoginDTO 统一账号接收登录信息
     * @return 返回 LoginTokenVO 信息
     */
    LoginTokenVO userLogin(UserLoginDTO userLoginDTO, HttpServletRequest request);

    /**
     *
     * @param userId 根据userId获取用户信息
     * @return UserInfoVO
     */
    UserInfoVO getUserInfo(Integer userId);

    /**
     *
     * @param refreshTokenDTO 刷新token
     * @return LoginTokenVO
     */
    LoginTokenVO refreshToken(RefreshTokenDTO refreshTokenDTO);

}
