package com.work.bench.dto.bill;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账单 收入 支出
 *
 * @author 洁心未眠
 * @Package com.work.bench.dto.bill
 * @date 2026/8/22 01:09
 */
@Data
public class BillExpIncDTO {
    /**
     * 支出
     */
    private BigDecimal expense;
    /**
     * 收入
     */
    private BigDecimal income;

}
