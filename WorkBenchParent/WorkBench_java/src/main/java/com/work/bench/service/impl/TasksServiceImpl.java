package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.TasksMapper;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.Tasks;
import com.work.bench.pojo.User;
import com.work.bench.service.TasksService;
import com.work.bench.service.UserService;
import com.work.bench.vo.tasks.TasksVO;
import lombok.RequiredArgsConstructor;
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
}
