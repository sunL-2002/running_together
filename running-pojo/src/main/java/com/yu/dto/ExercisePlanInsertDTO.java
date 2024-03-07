package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "运动计划创建传递的数据模型")
public class ExercisePlanInsertDTO implements Serializable {

    private int planId;

    @ApiModelProperty("创建人openid")
    private String initiatorId;

    @ApiModelProperty("开始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;

    @ApiModelProperty("计划目标")
    private String planGoal;
}
