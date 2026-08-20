package com.work.bench.controller;

import com.work.bench.service.BillAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户 web 层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:37
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Tag(name="账户相关")

public class BillAccountController {

    private final BillAccountService billAccountService;

}
