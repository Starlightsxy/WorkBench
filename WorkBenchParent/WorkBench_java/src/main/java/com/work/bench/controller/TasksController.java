package com.work.bench.controller;

import com.work.bench.annotation.AroundLog;
import com.work.bench.service.TasksService;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.Result;
import com.work.bench.vo.tasks.TasksVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务待办 web 层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 19:37
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
@Tag(name = "待办任务相关")

public class TasksController {

    private final TasksService tasksService;

    @AroundLog
    @GetMapping("/tasks")
    @Operation(
            summary = "获取任务待办",
            description = "根据用户id获取当前用户的任务待办列表"
    )
    public Result<List<TasksVO>> getTasks() {
        return Result.success(tasksService.getUserTasks(BaseContext.getCurrentId()));
    }

}
