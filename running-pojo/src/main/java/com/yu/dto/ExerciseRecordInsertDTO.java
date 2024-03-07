package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "用户运动记录时传递的数据模型")
public class ExerciseRecordInsertDTO implements Serializable {

    @ApiModelProperty("用户标识")
    private String userOpenid;

    @ApiModelProperty("跑步路线")
    private List<PlaylineDTO> runningRoute; //跑步路线

    @ApiModelProperty("距离")
    private float distance; //距离

    @ApiModelProperty("运动时间")
    private String time;  //运动时间
}
