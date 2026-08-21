package com.work.bench.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.bench.dto.bill.BillExpIncDTO;
import com.work.bench.dto.plan.BillPlanDTO;
import com.work.bench.pojo.Bill;
import com.work.bench.vo.bill.BillVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
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
     *
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
            "ba.account_icon," +

            "ba.balance," +
            "bc.category_icon " +
            "from tb_bill b " +
            "inner join tb_bill_category bc on b.category_id = bc.id " +
            "inner join tb_bill_account ba on b.account_id = ba.id " +
            "where b.user_id = #{userId} and b.deleted = 0 " +
            "order by b.create_time " +
            "desc limit 5;")
    List<BillVO> selectRecentBillList(Integer userId);


    /**
     * 查询 bill 当月 收支
     *
     * @param userId         根据当前用户id
     * @param monthStart     当月开始
     * @param nextMonthStart 截止下月
     * @return BigDecimal
     * <p>
     * 这里可以直接通过一次查询查出收支，不用为收支写两次查询
     * <p>
     * 由于我在数据库做了时间戳，并且还建立了索引，所以不可以用
     * WHERE YEAR(FROM_UNIXTIME(bill_date)) = 2026
     * AND MONTH(FROM_UNIXTIME(bill_date)) = 8 否则查询会导致索引失效，使用范围判断更利用与索引
     *
     */
    @Select("select " +
            "COALESCE(SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), 0) AS income," +
            "COALESCE(SUM(CASE WHEN type = 2 THEN amount ELSE 0 END), 0) AS expense " +
            " from tb_bill " +
            "where user_id = #{userId} " +
            "and deleted = 0 " +
            "and bill_date >= #{monthStart} " +
            "and bill_date < #{nextMonthStart}")
    BillExpIncDTO selectMonthExpInc(
            @Param("userId") Integer userId,
            @Param("monthStart") Long monthStart,
            @Param("nextMonthStart") Long nextMonthStart);

    /**
     * 查询当月预算和收入目标
     *
     * @param userId    根据当前用户id
     * @param currMonth 和当前月份
     * @return BillPlanDTO
     */
    @Select("select budget,income_goal from " +
            "tb_bill_plan " +
            "where user_id = #{userId} " +
            "and deleted = 0 " +
            "and plan_date = #{currMonth};")
    BillPlanDTO selectMonthBudIncomeGoal(
            @Param("userId") Integer userId,
            @Param("currMonth") Integer currMonth);


    /**
     * 当年 收支
     * 和月度一样，只不过这里是根据年度范围
     *
     * @param userId        当前用户id
     * @param yearStart     当年开始
     * @param nextYearStart 次年开始
     * @return
     */
    @Select("select " +
            "COALESCE(SUM(CASE WHEN type = 1 THEN amount ELSE 0 END), 0) AS income," +
            "COALESCE(SUM(CASE WHEN type = 2 THEN amount ELSE 0 END), 0) AS expense " +
            " from tb_bill " +
            "where user_id = #{userId} " +
            "and deleted = 0 " +
            "and bill_date >= #{yearStart} " +
            "and bill_date < #{nextYearStart}")
    BillExpIncDTO selectYearExpInc(@Param("userId") Integer userId,
                                   @Param("yearStart") Long yearStart,
                                   @Param("nextYearStart") Long nextYearStart);

    /**
     * 查询当年预算和收入目标
     *
     * @param userId   根据当前用户id
     * @param currYear 和当前年份
     * @return BillPlanDTO
     */
    @Select("select budget,income_goal from " +
            "tb_bill_plan " +
            "where user_id = #{userId} " +
            "and deleted = 0 " +
            "and plan_date = #{currYear};")
    BillPlanDTO selectYearBudIncomeGoal(
            @Param("userId") Integer userId,
            @Param("currYear") Integer currYear);


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
            "ba.account_icon," +

            "ba.balance," +
            "bc.category_icon " +
            "from tb_bill b " +
            "inner join tb_bill_category bc on b.category_id = bc.id " +
            "inner join tb_bill_account ba on b.account_id = ba.id " +
            "where b.user_id = #{userId} and b.deleted = 0 " +
            "order by b.bill_date desc")
    List<BillVO> selectBillListByUserId(Integer userId);

}
