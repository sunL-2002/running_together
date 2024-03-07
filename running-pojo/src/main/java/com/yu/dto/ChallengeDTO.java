package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "发起挑战传递的数据模型")
public class ChallengeDTO implements Serializable {

    @ApiModelProperty("发起人id")
    private int initiatorId;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("挑战内容")
    private String challengeContent;

    @ApiModelProperty("挑战标签")
    private String tag;
}
