package com.work.bench.controller;

import com.work.bench.service.AnniversaryService;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.Result;
import com.work.bench.vo.anniversary.AnniversaryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 纪念日 web 层
 *
 * @author 洁心未眠
 * @Package com.work.bench.controller
 * @date 2026/8/3 16:37
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/anniversary")
public class AnniversaryController {

    private final AnniversaryService anniversaryService;

    /**
     * 根据当前用户id查询纪念日信息
     * @return
     */
    @GetMapping("/anniversary/lists")
    public Result<List<AnniversaryVO>> listAnniversary() {
        return Result.success(anniversaryService.getListsByUserId(BaseContext.getCurrentId()));
    }

}
