package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.BillMapper;
import com.work.bench.pojo.Bill;
import com.work.bench.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 账单服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
}
