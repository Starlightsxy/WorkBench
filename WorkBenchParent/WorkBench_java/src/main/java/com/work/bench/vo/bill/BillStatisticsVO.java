package com.work.bench.vo.bill;

import com.work.bench.dto.bill.BillExpIncDTO;
import com.work.bench.dto.plan.BillPlanDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账单统计
 *
 * @author 洁心未眠
 * @Package com.work.bench.vo.plan
 * @date 2026/8/22 00:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillStatisticsVO {
    /**
     *
     * 支出和收入通过 bill 表进行聚合查询，
     */
    private BillExpIncDTO billExpIncDTO;

    /**
     * 预算和收入目标 通过 plan 表进行聚合
     */
    private BillPlanDTO billPlanDTO;
}
