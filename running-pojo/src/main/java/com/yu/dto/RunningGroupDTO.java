package com.yu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "跑团组织传递的数据模型")
public class RunningGroupDTO implements Serializable {

    @ApiModelProperty("名称")
    private String groupName;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("跑团介绍")
    private String groupDescription;

    @ApiModelProperty("创建人openid")
    private String initiatorId;
}
