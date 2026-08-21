package com.work.bench.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账单详情
 *
 * @author 洁心未眠
 * @Package com.work.bench.vo
 * @date 2026/8/21 16:48
 */
@Data
public class BillVO {
    private Integer id;
    /**
     * 支付时间
     */
    private Long billDate;
    /**
     * 支付类型
     * 1 收入
     * 2 支出
     */
    private String type;
    /**
     * 支付金额
     */
    private BigDecimal amount;
    /**
     * 详情描述
     */
    private String remark;
    /**
     * 支付类别
     */
    private String categoryName;
    /**
     * 支付方式
     */
    private String accountName;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 支付类别图标
     */
    private String icon;
}
