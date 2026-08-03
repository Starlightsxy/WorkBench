package com.work.bench.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 待办任务表
 *
 * @author 洁心未眠
 * @Package com.work.bench.pojo
 * @date 2026/8/3 19:28
 */
@TableName("tb_tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tasks {
    // 任务id
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 所属用户编号
    private Integer userId;
    // 任务标题
    private String title;
    // 任务描述
    private String description;
    //任务状态 0-待办 1-进行中 2-完成 3-取消
    private Integer status;
    // 任务级别 优先级 1-低 2-中 3-高
    private Integer priority;
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
