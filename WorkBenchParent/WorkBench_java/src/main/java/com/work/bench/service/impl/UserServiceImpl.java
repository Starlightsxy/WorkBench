package com.work.bench.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.enums.GenderType;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.User;
import com.work.bench.service.UserService;
import com.work.bench.utils.BaseContext;
import com.work.bench.vo.user.UserInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserMapper userMapper;

    /**
     * 用户登录方法
     *
     * @param userLoginDTO 统一账号接收登录信息
     * @return 返回 UserInfoVO 信息
     */
    @Override
    public UserInfoVO userLogin(UserLoginDTO userLoginDTO) {

        String account = userLoginDTO.getAccount();
        User user;

        if ((account == null || account.isEmpty())) {
            throw new BusinessException("账号不能为空");
        }
        if (userLoginDTO.getPassword() == null || userLoginDTO.getPassword().isEmpty()) {
            throw new BusinessException("密码不能为空");
        }

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
                        User::getTheme
                )
                .and(w ->
                        w.eq(User::getUserName, account)
                                .or()
                                .eq(User::getEmail, account)
                                .or()
                                .eq(User::getPhone, account)
                )
                .eq(User::getDeleted, 0);


        user = userMapper.selectOne(userWrapper);
        // 判断用户是否存在
        if (user == null) {
            throw new BusinessException("账号不存在或已禁用");
        }

        // 判断密码是否正确
        if (!BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 登录成功
        UserInfoVO userInfoVO = UserInfoVO.builder()
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



        // 将用户id存储在当前线程上
        BaseContext.setCurrentId(Long.valueOf(user.getId()));

        log.info("登录方法");

        return userInfoVO;
    }
}
