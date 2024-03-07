package com.yu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "请求组织信息返回的数据格式")
public class OrganizationsVO implements Serializable {

    @ApiModelProperty("主键值")
    private int groupId;

    @ApiModelProperty("组织名称")
    private String groupName;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("组织描述")
    private String groupDescription;
}
