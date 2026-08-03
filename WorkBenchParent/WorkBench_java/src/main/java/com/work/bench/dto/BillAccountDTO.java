package com.work.bench.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 账户表 dto
 *
 * @author 洁心未眠
 * @Package com.work.bench.dto
 * @date 2026/8/3 16:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillAccountDTO {

    // 账户表id
    private Integer id;
    // 用户id
    private Integer userId;
    // 账户名称
    private String name;
    // 账户余额
    private BigDecimal balance;
    // 图标
    private String icon;
    // 创建时间
    private Long createTime;
    // 修改时间
    private Long updateTime;
    // 逻辑删除 0 不删除  1 删除
    private Integer deleted;
}
