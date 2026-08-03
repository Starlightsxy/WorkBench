package com.work.bench.controller;

import com.work.bench.service.AnniversaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
