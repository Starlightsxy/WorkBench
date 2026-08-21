package com.work.bench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账单分类表
 *
 * @author 洁心未眠
 * @Package com.work.bench.pojo
 * @date 2026/8/3 16:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_bill_category")
public class BillCategory {
    // 账单分类表id
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 用户id
    private Integer userId;
    // 名称
    private String name;
    // 类型 1收入 2支出  普通索引
    private Integer type;
    // 图标
    private String categoryIcon;
    // 创建时间
    private Long createTime;
    // 修改时间
    private Long updateTime;
    // 逻辑删除 0 不删除  1 删除
    private Integer deleted;
}
