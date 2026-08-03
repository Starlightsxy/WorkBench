package com.work.bench.controller;

import com.work.bench.service.BillService;
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
public class BillController {

    private final BillService billService;

}
