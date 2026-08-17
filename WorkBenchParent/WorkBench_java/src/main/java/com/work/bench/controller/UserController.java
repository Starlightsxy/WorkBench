package com.work.bench.controller;

import com.work.bench.annotation.AroundLog;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.service.UserService;
import com.work.bench.utils.Result;
import com.work.bench.utils.SecurityUtils;
import com.work.bench.vo.user.LoginVO;
import com.work.bench.vo.user.UserInfoVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 web层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:34
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 用户登录信息
     * @param userLoginDTO 统一登录接收
     * @return 返回登录信息
     */
    @AroundLog
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        return Result.success("登录成功", userService.userLogin(userLoginDTO,request));
    }

    /**
     * 测试获取用户id
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<UserInfoVO> getUser(){
        return Result.success(SecurityUtils.getUserInfo());
    }
}
