package com.work.bench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 账单表
 *
 * @author 洁心未眠
 * @Package com.work.bench.pojo
 * @date 2026/8/3 16:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bill")
public class Bill {

    // 账单主键id
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户id
    private Integer userId;
    // 分类id 逻辑外键
    private Integer categoryId;
    // 账户id 逻辑外键
    private Integer accountId;
    // 账单类型 1收入 2支出
    private Integer type;
    // 金额
    private BigDecimal amount;
    // 备注
    private String remark;
    // 账单时间
    private Long billDate;
    // 创建时间
    private Long createTime;
    // 修改时间
    private Long updateTime;
    // 逻辑删除 0 不删除  1 删除
    private Integer deleted;
}
