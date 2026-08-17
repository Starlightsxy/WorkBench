package com.work.bench.vo.anniversary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 纪念日返回给前端的纪念日信息
 * @author 洁心未眠
 * @Package com.work.bench.vo.anniversary
 * @date 2026/8/13 18:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnniversaryVO implements Serializable {
    // 标题
    private String title;
    // 纪念日期
    private Long anniversaryDate;
    // 纪念日类型 1纪念日 2生日 3节日
    private String type;
    // 图标
    private String icon;
    // 颜色
    private String color;
    // 备注
    private String remark;
}
