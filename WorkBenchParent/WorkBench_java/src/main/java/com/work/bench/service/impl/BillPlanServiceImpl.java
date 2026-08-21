package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.BillPlanMapper;
import com.work.bench.pojo.BillPlan;
import com.work.bench.service.BillPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 财务计划服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class BillPlanServiceImpl extends ServiceImpl<BillPlanMapper, BillPlan> implements BillPlanService {
    private final BillPlanMapper billPlanMapper;

}
