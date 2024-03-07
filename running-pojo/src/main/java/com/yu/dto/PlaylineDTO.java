package com.yu.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "运动轨迹的数据模型")
public class PlaylineDTO implements Serializable {

    private double latitude;

    private double longitude;
}
