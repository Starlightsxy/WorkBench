package com.work.bench.controller;

import cn.hutool.system.UserInfo;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.service.UserService;
import com.work.bench.utils.Result;
import com.work.bench.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 web层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:34
 */
@CrossOrigin(
        origins = "http://localhost:5173"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 用户登录信息
     * @param userLoginDTO 统一登录接收
     * @return 返回登录信息
     */
    @PostMapping("/login")
    public Result<UserInfoVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        return Result.success("登录成功", userService.userLogin(userLoginDTO));
    }
}
