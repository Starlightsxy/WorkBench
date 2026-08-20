package com.work.bench.controller;

import com.work.bench.service.BillCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账单分类 web 层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:36
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name="账单类别相关")

public class BillCategoryController {

    private final BillCategoryService billCategoryService;

}
