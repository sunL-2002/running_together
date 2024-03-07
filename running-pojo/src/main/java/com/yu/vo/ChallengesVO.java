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
@ApiModel(description = "请求挑战信息返回的数据格式")
public class ChallengesVO implements Serializable {

    @ApiModelProperty("主键值")
    private int challengeId;

    @ApiModelProperty("图片路径")
    private String src;

    @ApiModelProperty("挑战内容")
    private String challengeContent;

    @ApiModelProperty("挑战标签")
    private String tag;


}
