package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "我的活动传递的数据模型")
public class GetInfoByTagDTO implements Serializable {

    @ApiModelProperty("请求标签")
    private String tag;

    @ApiModelProperty("openid")
    private String openid;
}
