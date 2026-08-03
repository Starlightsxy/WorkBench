package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.mapper.TasksMapper;
import com.work.bench.mapper.UserMapper;
import com.work.bench.pojo.Tasks;
import com.work.bench.pojo.User;
import com.work.bench.service.TasksService;
import com.work.bench.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
