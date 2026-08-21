package com.work.bench.controller;

import com.work.bench.annotation.AroundLog;
import com.work.bench.dto.user.RefreshTokenDTO;
import com.work.bench.dto.user.UserLoginDTO;
import com.work.bench.service.UserService;
import com.work.bench.utils.Result;
import com.work.bench.utils.SecurityUtils;
import com.work.bench.vo.user.LoginTokenVO;
import com.work.bench.vo.user.UserInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户相关")
public class UserController {

    private final UserService userService;

    /**
     * 用户登录信息
     *
     * @param userLoginDTO 统一登录接收
     * @return 返回登录信息
     */
    @AroundLog
    @PostMapping("/login")
    @Operation(
            summary = "用户登录",
            description = "用户操作登录，返回双token"
    )
    public Result<LoginTokenVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request) {
        return Result.success("登录成功", userService.userLogin(userLoginDTO, request));
    }

    /**
     * 测试获取用户id
     *
     * @return
     */
    @AroundLog
    @GetMapping("/getUserInfo")
    @Operation(
            summary = "获取用户信息",
            description = "用户信息包含在Security中，可直接获取"
    )
    public Result<UserInfoVO> getUser() {
        return Result.success(SecurityUtils.getUserInfo());
    }

    /**
     * 刷新token
     * @param refreshTokenDTO
     * @return
     */
    @AroundLog
    @PostMapping("/refresh")
    @Operation(
            summary = "刷新token",
            description = "根据前端的refreshToken 从redis中获取进行比对，成功则刷新一个accessToken给前端"
    )
    public Result<LoginTokenVO> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        return Result.success(userService.refreshToken(refreshTokenDTO));
    }
}
