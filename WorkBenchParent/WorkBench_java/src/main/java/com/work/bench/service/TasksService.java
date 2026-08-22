package com.work.bench.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.bench.dto.tasks.TasksDTO;
import com.work.bench.pojo.Tasks;
import com.work.bench.pojo.User;
import com.work.bench.vo.tasks.TasksVO;

import java.util.List;

/**
 * 任务待办服务
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
public interface TasksService extends IService<Tasks> {
    /**
     * 获取待办任务集合
     * @param userId userId 作为条件
     * @return 返回任务集合
     */
    List<TasksVO> getUserTasks(Integer userId);

    /**
     * 对任务进行修改，或完成或新增
     * @param tasksDTO 任务实体类
     * @return
     */
    boolean saveTask(TasksDTO tasksDTO);
}
