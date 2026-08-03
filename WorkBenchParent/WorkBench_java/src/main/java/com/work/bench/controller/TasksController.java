package com.work.bench.controller;

import com.work.bench.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class TasksController {

    private final TasksService tasksService;

}
