package com.work.bench.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.bench.pojo.Bill;
import com.work.bench.vo.BillVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 账单 mapper
 *
 * @author 洁心未眠
 * @Package com.work.bench.mapper
 * @date 2026/8/3 16:17
 */
public interface BillMapper extends BaseMapper<Bill> {
    /**
     * 获取当前用户最近5条账单
     * @param userId 根据当前 userId 查询当前用户最近5条账单
     * @return 返回账单集合
     */
    @Select(" select b.id, b.bill_date ," +
            "   CASE b.type " +
            "       when 1 then '收入' " +
            "       when 2 then '支出' " +
            "   else '未知' " +
            "   end as type, " +
            "b.amount," +
            "b.remark," +
            "bc.category_name," +
            "ba.account_name," +
            "ba.balance," +
            "bc.icon " +
            "from tb_bill b " +
            "inner join tb_bill_category bc on b.category_id = bc.id " +
            "inner join tb_bill_account ba on b.account_id = ba.id " +
            "where b.user_id = 1 " +
            "order by b.create_time " +
            "desc limit 5;")
    List<BillVO> selectBillListByUserId(Integer userId);
}
