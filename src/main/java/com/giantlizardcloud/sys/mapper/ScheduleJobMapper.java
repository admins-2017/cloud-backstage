package com.giantlizardcloud.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.giantlizardcloud.dto.job.ScheduleJobWithDetail;
import com.giantlizardcloud.sys.entity.ScheduleJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

    @Select("select a.*,b.bean_name,b.method_name,b.method_arg_type from sys_schedule_job a\n" +
            " LEFT JOIN sys_schedule_detail b on a.detail_id = b.id\n" +
            " where a.status = 1 and a.delete_flag = 0")
    List<ScheduleJobWithDetail> getTimingTasks();

    @Select("select a.*,b.bean_name,b.method_name,b.method_arg_type from sys_schedule_job a LEFT JOIN sys_schedule_detail b on a.detail_id = b.id where a.id = #{id}")
    ScheduleJobWithDetail getTaskId(@Param("id") Integer id);

    List<ScheduleJobWithDetail> getTaskByPage(@Param("page") Integer page ,@Param("size") Integer size);

    List<ScheduleJobWithDetail> getTaskByLikeName(@Param("page") Integer page ,@Param("size") Integer size,@Param("likeName") String likeName);

}
