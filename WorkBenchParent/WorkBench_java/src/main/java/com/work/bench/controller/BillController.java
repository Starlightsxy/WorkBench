package com.work.bench.controller;

import com.work.bench.service.BillService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账单 web 层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/bill")
@Tag(name="账单相关")

public class BillController {

    private final BillService billService;

}
