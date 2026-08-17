package com.work.bench.service.impl.logserviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.login.LoginLogMapper;
import com.work.bench.pojo.logo.LoginLog;
import com.work.bench.service.logservice.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/17 16:05
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
}
