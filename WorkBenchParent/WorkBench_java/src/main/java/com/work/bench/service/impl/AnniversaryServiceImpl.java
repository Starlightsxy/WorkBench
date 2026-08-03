package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.AnniversaryMapper;
import com.work.bench.pojo.Anniversary;
import com.work.bench.service.AnniversaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 纪念日服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class AnniversaryServiceImpl extends ServiceImpl<AnniversaryMapper, Anniversary> implements AnniversaryService {
}
