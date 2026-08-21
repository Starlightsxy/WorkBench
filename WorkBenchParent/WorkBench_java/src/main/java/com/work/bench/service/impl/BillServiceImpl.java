package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.enums.BillType;
import com.work.bench.enums.DeleteStatus;
import com.work.bench.mapper.BillAccountMapper;
import com.work.bench.mapper.BillMapper;
import com.work.bench.pojo.Bill;
import com.work.bench.service.BillService;
import com.work.bench.utils.TimeUtils;
import com.work.bench.vo.bill.BillPageVO;
import com.work.bench.vo.bill.BillStatisticsVO;
import com.work.bench.vo.bill.BillVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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
    // 账单mapper
    private final BillMapper billMapper;
    // 财务mapper
    private final BillAccountMapper billAccountMapper;

    /**
     * 获取当前用户最近5条账单集合
     *
     * @param userId 根据用户 id 查询用户的账单集合
     * @return 返回账单集合
     */
    @Override
    public List<BillVO> getUserRecentBills(Integer userId) {
        if (userId == null) {
            return Collections.emptyList();   // 或抛出异常
        }
        return billMapper.selectRecentBillList(userId);
    }

    /**
     * 查询账单页，包含财务计算
     *
     * @param userId 根据用户id
     * @return BillPageVO
     */
    @Override
    public BillPageVO getBillPage(Integer userId) {
        if (userId == null) {
            return new BillPageVO();   // 或抛出异常
        }
        // 统一指定时区
        LocalDate now = LocalDate.now(TimeUtils.ZONE_ID);

        BillPageVO billPageVO = new BillPageVO();
        // 1.查询 bill_account 计算当前用户的总资产
        billPageVO.setTotalAsset(billAccountMapper.selectTotalAsset(userId));

        // 2. 月度统计
        BillStatisticsVO month = new BillStatisticsVO();
        // bill 聚合 月度 收支
        // 时间范围 当月月初-下月月初之间
        TimeUtils.TimeRange rangeMonth = TimeUtils.getCurrentMonthRange();
        month.setBillExpIncDTO(billMapper.selectMonthExpInc(userId, rangeMonth.start(), rangeMonth.end()));
        // plan 聚合 月度 预算 和 收入
        // 时间范围 当月
        Integer currMonth = now.getYear() * 100 + now.getMonthValue();
        month.setBillPlanDTO(billMapper.selectMonthBudIncomeGoal(userId, currMonth));


        // 3. 年度统计
        BillStatisticsVO year = new BillStatisticsVO();
        // bill 聚合 年度 收支
        // 时间范围 当年1月1日初-次年1月1日初 之间范围
        TimeUtils.TimeRange rangeYear = TimeUtils.getCurrentYearRange();
        year.setBillExpIncDTO(billMapper.selectYearExpInc(userId, rangeYear.start(), rangeYear.end()));
        // plan 聚合 年度 预算和收入目标
        Integer currYear = now.getYear();
        year.setBillPlanDTO(billMapper.selectYearBudIncomeGoal(userId, currYear));

        billPageVO.setMonth(month);
        billPageVO.setYear(year);

        // 获取账单
        // TODO 这里暂时不做分页
        List<BillVO> billVOS = billMapper.selectBillListByUserId(userId);
        billPageVO.setRecords(billVOS);
        // 获取账单数量
        billPageVO.setTotal(billVOS.size());

        return billPageVO;
    }
}
