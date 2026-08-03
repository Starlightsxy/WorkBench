package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.BillCategoryMapper;
import com.work.bench.pojo.BillCategory;
import com.work.bench.service.BillCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 账单分类服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class BillCategoryServiceImpl extends ServiceImpl<BillCategoryMapper, BillCategory> implements BillCategoryService {
}
