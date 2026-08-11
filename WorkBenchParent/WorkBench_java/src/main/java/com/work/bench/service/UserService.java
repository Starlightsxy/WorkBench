package com.work.bench.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.pojo.User;
import com.work.bench.vo.user.LoginVO;
import com.work.bench.vo.user.UserInfoVO;

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
     * @return 返回 LoginVO 信息
     */
    LoginVO userLogin(UserLoginDTO userLoginDTO);

    UserInfoVO getUserInfo(Integer userId);
}
