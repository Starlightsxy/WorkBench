package com.work.bench.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.bench.pojo.Tasks;
import com.work.bench.vo.tasks.TasksVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 待办任务 Mapper
 *
 * @author 洁心未眠
 * @Package com.work.bench.mapper
 * @date 2026/8/3 19:31
 */
@Mapper
public interface TasksMapper extends BaseMapper<Tasks> {
    /**
     * 获取任务集合
     * @param userId 根据用户 id 查询任务待办
     * @return 返回任务集合
     */
    @Select("select id,title," +
            "       description," +
            "       CASE status" +
            "           WHEN 1 THEN '进行中'" +
            "           WHEN 2 THEN '已完成'" +
            "           WHEN 3 THEN '已取消'" +
            "           ELSE '未知'" +
            "           END AS status," +
            "       due_time , " +
            "       completed_time," +
            "       create_time," +
            "       update_time" +
            " from tb_tasks where deleted = 0 and user_id = #{userId} order by due_time ")
    List<TasksVO> selectTaskListByUserId(@Param("userId") Integer userId);
}
