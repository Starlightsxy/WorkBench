package com.work.bench.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.bench.pojo.BillAccount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

/**
 * 账户 mapper
 *
 * @author 洁心未眠
 * @Package com.work.bench.mapper
 * @date 2026/8/3 16:19
 */
public interface BillAccountMapper extends BaseMapper<BillAccount> {

    /**
     * 查询账户总资产
     *
     * @param userId 根据用户id 和 未删除进行查询
     * @return
     */
    @Select("SELECT COALESCE(SUM(balance), 0)  FROM tb_bill_account WHERE user_id = #{userId} AND deleted = 0")
    BigDecimal selectTotalAsset(Integer userId);
}
