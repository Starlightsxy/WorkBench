package com.work.bench.vo.bill;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 总账单列表页
 * @author 洁心未眠
 * @Package com.work.bench.vo.bill
 * @date 2026/8/22 00:04
 */
@Data
public class BillPageVO {
    /**
     * 总资产 ;
     */
    private BigDecimal totalAsset;
    /**
     * 本月数据
     */
    private BillStatisticsVO month;
    /**
     * 年度数据
     */
    private BillStatisticsVO year;
    /**
     * 账单列表
     */
    private List<BillVO> records;
    /**
     * 账单总数
     */
    private Integer total;
}
