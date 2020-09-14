package com.giantlizardcloud.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    @ApiModelProperty(value = "id",example = "1")
    private Integer id;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "表达式")
    private String cronExpression;

    @ApiModelProperty(value = "执行类的bean")
    private String beanName;

    @ApiModelProperty(value = "执行方法")
    private String methodName;

    @ApiModelProperty(value = "状态 1.启动 2.暂停",example = "1")
    private Integer status;

    @ApiModelProperty(value = "是否删除 0.否 1.是",example = "1")
    private Boolean deleteFlag;
}
