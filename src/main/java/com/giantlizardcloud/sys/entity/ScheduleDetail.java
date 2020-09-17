package com.giantlizardcloud.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_schedule_detail")
@ApiModel(value="ScheduleDetail对象", description="")
public class ScheduleDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "执行类的bean名称")
    private String beanName;

    @ApiModelProperty(value = "执行方法名称")
    private String methodName;

    @ApiModelProperty(value = "执行方法参数类型")
    private String methodArgType;


}
