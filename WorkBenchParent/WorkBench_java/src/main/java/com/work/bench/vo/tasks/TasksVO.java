package com.work.bench.vo.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务展示 VO
 * @author 洁心未眠
 * @Package com.work.bench.vo.tasks
 * @date 2026/8/20 19:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksVO {
    // 任务编号
    private Integer id;
    // 任务标题
    private String title;
    // 任务描述
    private String description;
    //任务状态 1-进行中 2-完成 3-取消 默认1
    private String status;
    // 截至时间
    private Long dueTime;
    // 实际完成时间
    private Long completedTime;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;
}
