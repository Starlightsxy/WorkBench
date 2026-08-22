package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.common.BaseEnum;
import com.work.bench.dto.tasks.TasksDTO;
import com.work.bench.enums.DeleteStatus;
import com.work.bench.enums.TasksStatus;
import com.work.bench.exception.BusinessException;
import com.work.bench.mapper.TasksMapper;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.Tasks;
import com.work.bench.pojo.User;
import com.work.bench.service.TasksService;
import com.work.bench.service.UserService;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.EnumUtils;
import com.work.bench.vo.tasks.TasksVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 任务待办服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements TasksService {
    private final TasksMapper tasksMapper;

    /**
     * 获取待办任务集合
     *
     * @param userId userId 作为条件
     * @return
     */
    @Override
    public List<TasksVO> getUserTasks(Integer userId) {
        if (userId == null) {
            return Collections.emptyList();   // 或抛出异常
        }
        return tasksMapper.selectTaskListByUserId(userId);
    }

    /**
     * 对任务进行修改，或完成或新增
     *
     * @param tasksDTO 任务实体类
     * @return
     */
    @Override
    public boolean saveTask(TasksDTO tasksDTO) {
        if (tasksDTO == null) {
            throw new BusinessException("任务不能为空");
        }

        Tasks tasks = new Tasks();
        BeanUtils.copyProperties(tasksDTO, tasks);
        tasks.setStatus(EnumUtils.getCodeByDesc(TasksStatus.class, tasksDTO.getStatus()));
        tasks.setUserId(BaseContext.getCurrentId());
        long timestamp = System.currentTimeMillis() / 1000;

        // 修改操作，更改一下任务的更新时间
        if (tasks.getId() != 0) {
            // 设置任务的完成状态时需要添加实际完成时间
            if (tasks.getStatus() != null && tasks.getStatus().equals(TasksStatus.COMPLETED.getCode())) {
                // 设置一下实际完成时间 和任务的更新时间
                tasks.setCompletedTime(timestamp);
                tasks.setUpdateTime(timestamp);
            }
            // 对任务的 删除 操作 只需要设置逻辑删除为0 即可
            if (tasks.getStatus() != null && tasks.getDeleted().equals(DeleteStatus.DELETED.getCode())) {
                // 设置逻辑删除为0
                tasks.setDeleted(DeleteStatus.DELETED.getCode());
            }
            // 对于取消操作不需要设置任何，只需要设置更新时间就行了，状态已经传过来了
            // 设置任务的更新时间
            tasks.setUpdateTime(timestamp);
        }

        // id等于null的情况下就是新增，这里要设置新增时间和修改时间
        if (tasks.getId() == 0) {
            tasks.setCreateTime(timestamp);
            tasks.setUpdateTime(timestamp);
        }
        // 保存或修改任务
        return this.saveOrUpdate(tasks);
    }
}
