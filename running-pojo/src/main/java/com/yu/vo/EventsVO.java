package com.yu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "请求活动信息返回的数据格式")
public class EventsVO implements Serializable {

    @ApiModelProperty("主键值")
    private int eventId;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("活动名称")
    private String eventName;

    @ApiModelProperty("活动描述")
    private String eventDescription;

    @ApiModelProperty("活动开始时间")
    private LocalDateTime startDatetime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endDatetime;
}
