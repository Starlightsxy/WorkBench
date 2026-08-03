package com.work.bench.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 纪念日 dto
 *
 * @author 洁心未眠
 * @Package com.work.bench.dto
 * @date 2026/8/3 16:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnniversaryDTO {

    // 纪念日主键id
    private Integer id;
    // 逻辑关联用户id
    private Integer userId;
    // 标题
    private String title;
    // 纪念日期
    private Long anniversaryDate;
    // 纪念日类型 1纪念日 2生日 3节日
    private Integer type;
    // 是否每年重复  1是 2否
    private Integer repeatFlag;
    // 图标
    private String icon;
    // 颜色
    private String color;
    // 备注
    private String remark;
    // 创建时间
    private Long createTime;
    // 修改时间
    private Long updateTime;
    // 逻辑删除 0 不删除  1 删除
    private Integer deleted;
}
