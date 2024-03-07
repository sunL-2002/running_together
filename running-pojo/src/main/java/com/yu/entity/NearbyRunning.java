package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * 同城约跑表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NearbyRunning implements Serializable {

    private int runId;

    private int initiatorId; //发起人ID

    private int acceptorId;  //接受人ID

    private LocalDateTime runDatetime;  //约跑日期时间

    private String runLocation; //约跑地点
}
