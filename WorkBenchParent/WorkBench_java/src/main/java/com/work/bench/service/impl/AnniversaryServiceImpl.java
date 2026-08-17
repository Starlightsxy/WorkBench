package com.work.bench.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.bench.config.RabbitMQConfig;
import com.work.bench.enums.AnniversaryType;
import com.work.bench.mapper.AnniversaryMapper;
import com.work.bench.pojo.Anniversary;
import com.work.bench.service.AnniversaryService;
import com.work.bench.utils.BaseContext;
import com.work.bench.utils.EnumUtils;
import com.work.bench.vo.anniversary.AnniversaryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 纪念日服务实现类
 *
 * @author 洁心未眠
 * @Package com.work.bench.service.impl
 * @date 2026/8/3 16:20
 */
@RequiredArgsConstructor
@Service
public class AnniversaryServiceImpl extends ServiceImpl<AnniversaryMapper, Anniversary> implements AnniversaryService {
    private final AnniversaryMapper anniversaryMapper;

    /**
     * 查询当前用户的纪念日
     *
     * @param userId 根据当前用户id查询
     * @return
     */
    @Override
    public List<AnniversaryVO> getListsByUserId(Integer userId) {
        List<Anniversary> anniversaries = anniversaryMapper.selectAnniversarysByUserId(userId);
        List<AnniversaryVO> anniversaryVOList = new ArrayList<>();
        anniversaries.forEach(item -> {
            AnniversaryVO anniversaryVO = new AnniversaryVO(
                    item.getTitle(),
                    item.getAnniversaryDate(),
                    EnumUtils.getDescByCode(AnniversaryType.class, item.getType()),

                    item.getIcon(),
                    item.getColor(),
                    item.getRemark());
            anniversaryVOList.add(anniversaryVO);
        });

        return anniversaryVOList;
    }
}
