package com.work.bench.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.bench.pojo.Bill;
import com.work.bench.vo.bill.BillPageVO;
import com.work.bench.vo.bill.BillVO;

import java.util.List;

/**
 * 账单服务
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */

public interface BillService extends IService<Bill> {
    /**
     * 获取当前用户最近 5条 账单集合
     * @param userId 根据用户 id 查询用户的账单集合
     * @return 返回账单集合
     */
    List<BillVO> getUserRecentBills(Integer userId);

    /**
     * 查询账单页，包含财务计算
     * @param userId 根据用户id
     * @return BillPageVO
     */
    BillPageVO getBillPage(Integer userId);
}
