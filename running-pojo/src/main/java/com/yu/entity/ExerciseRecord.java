package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 运动记录表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecord implements Serializable {

    private int recordId;

    private String userOpenid;

    private LocalDate exerciseDate;  //运动日期

    private String runningRoute; //跑步路线

    private float distance; //距离

    private String time;  //运动时间
}
