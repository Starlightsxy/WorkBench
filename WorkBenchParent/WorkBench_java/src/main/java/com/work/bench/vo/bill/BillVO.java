package com.work.bench.vo.bill;

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
    /**
     * 账单id
     */
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
     * 消费类别
     */
    private String categoryName;
    /**
     * 类别图标
     */
    private String categoryIcon;
    /**
     * 支付账户
     */
    private String accountName;
    /**
     * 账户图标
     */
    private String accountIcon;
    /**
     * 账户余额
     */
    private BigDecimal balance;
}
