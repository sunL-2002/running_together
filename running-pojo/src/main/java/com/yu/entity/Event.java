package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    private int eventId;

    private String eventName; //活动名称

    private String src;//图片路径

    private String eventDescription; //活动描述

    private LocalDateTime startDatetime; //活动开始时间

    private LocalDateTime endDatetime; //结束时间

    private String initiatorId; //创建人openid
}
