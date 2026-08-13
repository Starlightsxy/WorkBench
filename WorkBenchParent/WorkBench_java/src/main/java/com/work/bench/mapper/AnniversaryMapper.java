package com.work.bench.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.bench.pojo.Anniversary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 纪念日 mapper
 *
 * @author 洁心未眠
 * @Package com.work.bench.mapper
 * @date 2026/8/3 16:20
 */
public interface AnniversaryMapper extends BaseMapper<Anniversary> {
    @Select("select title,type,icon,remark,color,anniversary_date from tb_anniversary where user_id = #{userId}")
    List<Anniversary> selectAnniversarysByUserId(@Param("userId") Integer userId);
}
