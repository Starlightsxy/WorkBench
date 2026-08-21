package com.work.bench.dto.plan;

import lombok.Data;

import java.math.BigDecimal;

/**
 * plan  预算 收入目标
 *
 * @author 洁心未眠
 * @Package com.work.bench.vo.plan
 * @date 2026/8/22 01:06
 */
@Data
public class BillPlanDTO {
    /**
     * 预算
     */
    private BigDecimal budget;

    /**
     * 收入目标
     */
    private BigDecimal incomeGoal;
}
