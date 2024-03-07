package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 活动成员表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventMember implements Serializable {
    private int id;

    private String userOpenid;

    private int eventId;
}
