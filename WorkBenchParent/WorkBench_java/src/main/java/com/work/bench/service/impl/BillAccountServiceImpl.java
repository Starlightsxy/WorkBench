package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.BillAccountMapper;
import com.work.bench.pojo.BillAccount;
import com.work.bench.service.BillAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 账户服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class BillAccountServiceImpl extends ServiceImpl<BillAccountMapper, BillAccount> implements BillAccountService {
}
