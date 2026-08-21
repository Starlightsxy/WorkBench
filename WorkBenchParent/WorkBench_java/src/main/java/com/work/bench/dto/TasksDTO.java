package com.work.bench.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 洁心未眠
 * @Package com.work.bench.dto
 * @date 2026/8/3 19:36
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TasksDTO {
    // 任务id
    private Integer id;
    // 所属用户编号
    private Integer userId;
    // 任务标题
    private String title;
    // 任务描述
    private String description;
    //任务状态 0-待办 1-进行中 2-完成 3-取消
    private Integer status;
    // 截至时间
    private Long dueTime;
    // 实际完成时间
    private Long completedTime;
    // 创建时间
    private Long createTime;
    // 更新时间
    private Long updateTime;
    // '逻辑删除 0 不删除  1 删除'
    private Integer deleted;
}
