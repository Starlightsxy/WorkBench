package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.enums.GenderType;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.User;
import com.work.bench.security.JwtUtil;
import com.work.bench.security.LoginUser;
import com.work.bench.service.UserService;
import com.work.bench.vo.user.LoginVO;
import com.work.bench.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    /**
     * 用户登录方法
     *
     * @param userLoginDTO 统一账号接收登录信息
     * @return 返回 UserInfoVO 信息
     */
    @Override
    public LoginVO userLogin(UserLoginDTO userLoginDTO) {
        log.info("开始认证");
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();

        if ((account == null || account.isEmpty())) {
            throw new BusinessException("账号不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new BusinessException("密码不能为空");
        }

        // spring security 认证
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account, password)
            );
        } catch (LockedException e) {
            log.error(e.getMessage());
            throw new BusinessException("账号已删除");

        } catch (DisabledException e) {
            log.error(e.getMessage());
            throw new BusinessException("账号已禁用");

        } catch (AccountExpiredException e) {
            log.error(e.getMessage());
            throw new BusinessException("账号已过期");

        } catch (CredentialsExpiredException e) {
            log.error(e.getMessage());
            throw new BusinessException("密码已过期");

        } catch (BadCredentialsException e) {
            log.error(e.getMessage());
            throw new BusinessException("账号或密码错误");

        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Integer userId = loginUser.getUser().getId();

        // 生成 token
        String token = jwtUtil.createToken(userId);

        // 查询用户信息
        UserInfoVO userInfo = getUserInfo(userId);
        // 封装成VO返回前端
        return new LoginVO(token, userInfo);
    }

    /**
     * 根据用户id，获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoVO getUserInfo(Integer userId) {
        User user = getById(userId);
        return UserInfoVO.builder()
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .phone(user.getPhone())
                .signature(user.getSignature())
                .birthday(user.getBirthday())
                .theme(user.getTheme())
                .gender(GenderType.getDescByCode(user.getGender()))
                .build();
    }
}
