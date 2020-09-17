package com.giantlizardcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_schedule_job")
@ApiModel(value="ScheduleJob对象", description="")
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",example = "1")
    @TableId(type = IdType.AUTO )
    private Integer id;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务介绍")
    private String jobIntroduction;

    @ApiModelProperty(value = "表达式")
    private String cronExpression;

    @ApiModelProperty(value = "任务详情id",example = "1")
    private Integer detailId;

    @ApiModelProperty(value = "方法参数")
    private String methodParams;

    @ApiModelProperty(value = "状态 1.启动 2.暂停",example = "1")
    private Integer status;

    @ApiModelProperty(value = "是否删除 0.否 1.是",example = "1")
    private Boolean deleteFlag;

    @ApiModelProperty(value = "创建人id",example = "1")
    @TableField(fill = FieldFill.INSERT)
    private Long creatorId;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String creatorName;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updatedTime;


}
