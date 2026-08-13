package com.work.bench.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.work.bench.pojo.Anniversary;
import com.work.bench.vo.anniversary.AnniversaryVO;

import java.util.List;

/**
 * 纪念日服务
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
public interface AnniversaryService extends IService<Anniversary> {
    /**
     * 查询当前用户id的纪念日
     * @param userId 根据当前用户id查询
     * @return
     */
    List<AnniversaryVO> getListsByUserId(Integer userId);
}
