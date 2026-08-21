package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.BillMapper;
import com.work.bench.pojo.Bill;
import com.work.bench.service.BillService;
import com.work.bench.vo.BillVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
    private final BillMapper billMapper;

    /**
     * 获取当前用户最近5条账单集合
     *
     * @param userId 根据用户 id 查询用户的账单集合
     * @return 返回账单集合
     */
    @Override
    public List<BillVO> getUserBills(Integer userId) {
        if (userId == null) {
            return Collections.emptyList();   // 或抛出异常
        }
        return billMapper.selectBillListByUserId(userId);
    }
}
