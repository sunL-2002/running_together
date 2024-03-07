package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 跑团组织表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunningGroup implements Serializable {

    private int groupId;

    private String groupName; //名称

    private String src;//图片路径

    private String groupDescription;  //跑团介绍

    private String initiatorId; //创建人id
}
