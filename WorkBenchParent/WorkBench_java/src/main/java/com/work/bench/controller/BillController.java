package com.work.bench.controller;

import com.work.bench.annotation.AroundLog;
import com.work.bench.service.BillService;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.Result;
import com.work.bench.vo.bill.BillPageVO;
import com.work.bench.vo.bill.BillVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@Tag(name = "账单相关")

public class BillController {

    private final BillService billService;

    @GetMapping("/recent")
    @AroundLog
    @Operation(summary = "获取最近账单", description = "获取当前用户最近5条账单记录")
    public Result<List<BillVO>> getUserRecentBills() {
        return Result.success(billService.getUserRecentBills(BaseContext.getCurrentId()));
    }

    @GetMapping("/overview")
    @AroundLog
    @Operation(summary = "获取账单概览", description = "获取当前用户所有账单及财务信息")
    public Result<BillPageVO> getBillOverview() {
        return Result.success(billService.getBillPage(BaseContext.getCurrentId()));
    }
}
