package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "活动创建传递的数据模型")
public class EventDTO implements Serializable {

    @ApiModelProperty("活动名称")
    private String eventName;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("活动描述")
    private String eventDescription;

    @ApiModelProperty("创建人openid")
    private String initiatorId;

    @ApiModelProperty("活动开始时间")
    private String startDatetime;

    @ApiModelProperty("结束时间")
    private String endDatetime;
}
