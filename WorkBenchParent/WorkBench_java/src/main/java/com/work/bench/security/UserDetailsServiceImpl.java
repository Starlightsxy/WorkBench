package com.work.bench.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户详情服务实现
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 16:37
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;


    /**
     * spring security 认证入口
     *
     * @param userName account 多种方式登录
     * @return 实际上接收的是 account
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 不同登陆方式
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.select(
                        User::getId,
                        User::getUserName,
                        User::getNickName,
                        User::getPassword,
                        User::getAvatar,
                        User::getEmail,
                        User::getPhone,
                        User::getGender,
                        User::getBirthday,
                        User::getSignature,
                        User::getTheme,
                        User::getDeleted
                )
                .and(w ->
                        w.eq(User::getUserName, userName)
                                .or()
                                .eq(User::getEmail, userName)
                                .or()
                                .eq(User::getPhone, userName)
                );


        User user = userMapper.selectOne(userWrapper);
        // 判断用户是否存在
        if (user == null) {
            throw new BusinessException("账号不存在或已禁用");
        }
        return new LoginUser(user);
    }
}
