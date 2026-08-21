package com.work.bench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 财务计划表
 *
 * @author 洁心未眠
 * @Package com.work.bench.pojo
 * @date 2026/8/21 23:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_bill_plan")
public class BillPlan {
    // 财务计划表id
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户id
    private Integer userId;
    // 计划类型 1月度 2年度
    private Integer planType;
    // 计划日期
    private Integer planDate;
    // 支出预算
    private BigDecimal budget;
    // 收入目标
    private BigDecimal incomeGoal;
    //    创建时间
    private Long createTime;
    //    更新时间
    private Long updateTime;
    // '逻辑删除 0 不删除  1 删除'
    private Integer deleted;
}
