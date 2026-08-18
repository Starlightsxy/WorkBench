package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.config.RabbitMQConfig;
import com.work.bench.dto.User.RefreshTokenDTO;
import com.work.bench.dto.User.UserLoginDTO;
import com.work.bench.enums.GenderType;
import com.work.bench.enums.LoginLogStatus;
import com.work.bench.enums.RedisCacheKey;
import com.work.bench.enums.TokenType;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.User;
import com.work.bench.rabbitmq.message.UserLoginMessage;
import com.work.bench.utils.EnumUtils;
import com.work.bench.utils.JwtUtil;
import com.work.bench.security.LoginUser;
import com.work.bench.service.UserService;
import com.work.bench.utils.RequestUtils;
import com.work.bench.vo.user.LoginTokenVO;
import com.work.bench.vo.user.UserInfoVO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

    private final RedisTemplate<String, Object> jsonRedisTemplate;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final RabbitTemplate rabbitTemplate;

    /**
     * 用户登录方法
     *
     * @param userLoginDTO 统一账号接收登录信息
     * @return 返回 LoginTokenVO 信息
     */
    @Override
    public LoginTokenVO userLogin(UserLoginDTO userLoginDTO, HttpServletRequest request) {

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

        // 生成 两个token
        String accessToken = jwtUtil.createToken(userId, TokenType.ACCESS);
        String refreshToken = jwtUtil.createToken(userId, TokenType.REFRESH);

        // 查询用户信息
        UserInfoVO userInfoVO = getUserInfo(userId);

        // 用户信息存储在redis中 使用和token一样的过期时间
        jsonRedisTemplate.opsForValue().set(
                RedisCacheKey.REDIS_CACHE_USER_KEY.getValue() + userId,
                userInfoVO,
                JwtUtil.getRefreshExpireTime(),
                TimeUnit.MILLISECONDS
        );

        // 存储 refreshToken 在redis中
        String key = RedisCacheKey.REFRESH_TOKEN.getValue() + userId;
        jsonRedisTemplate.opsForValue().set(
                key,
                refreshToken,
                JwtUtil.getRefreshExpireTime(),
                TimeUnit.MILLISECONDS
        );

        /**
         * 构建消息体
         * // TODO 这里构建消息体这里的登陆状态固定了，还有失败原因还没做处理
         */
        UserLoginMessage userLoginMessage = UserLoginMessage.builder()
                .userId(userId)
                .account(account)
                .loginIp(RequestUtils.getClientIp(request))
                .loginStatus(LoginLogStatus.SUCCESS.getCode())
                .failReason("")
                .userAgent(request.getHeader("user-agent"))
                .loginTime(System.currentTimeMillis() / 1000)
                .build();
        // 发送消息队列给消费者
        rabbitTemplate.convertAndSend(RabbitMQConfig.USER_EXCHANGE, RabbitMQConfig.USER_LOGIN_ROUTING_KEY, userLoginMessage);

        // 封装成VO返回前端
        return new LoginTokenVO(accessToken, refreshToken);
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
                .gender(EnumUtils.getDescByCode(GenderType.class, user.getGender()))
                .build();
    }

    /**
     *
     * @param refreshTokenDTO 刷新token
     * @return LoginTokenVO
     */
    @Override
    public LoginTokenVO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        String refreshToken = refreshTokenDTO.getRefreshToken();
        // 解析 refreshToken
        Claims claims = jwtUtil.validateToken(refreshToken);
        if (claims == null) {
            throw new BusinessException("无效的Refresh Token");
        }
        // 判断 token 类型
        String type = claims.get("type", String.class);
        // 如果不是refresh就报错无效
        if (!"refresh".equals(type)) {
            throw new BusinessException("无效的Refresh Token");
        }
        // 获取用户的id
        Integer userId = Integer.parseInt(claims.getSubject());

        // 在redis中找到对应的refreshToken做比较
        String key = RedisCacheKey.REFRESH_TOKEN.getValue() + userId;
        String redisRefreshToken = (String) jsonRedisTemplate.opsForValue().get(key);

        if (redisRefreshToken == null) {
            throw new BusinessException("RefreshToken 过期或禁用，需要重新登录");
        }
        if (!redisRefreshToken.equals(refreshToken)) {
            throw new BusinessException(
                    "RefreshToken 过期或禁用，需要重新登录"
            );
        }
        // 重新生成 Access Token
        String accessToken = jwtUtil.createToken(userId, TokenType.ACCESS);

        return new LoginTokenVO(accessToken, refreshToken);
    }
}
