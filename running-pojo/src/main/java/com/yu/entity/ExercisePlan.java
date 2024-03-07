package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * 运动计划表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePlan implements Serializable {

    private int planId;

    private int userId;

    private LocalDate startDate; //开始日期

    private LocalDate endDate; //结束日期

    private String planGoal; //计划目标
}
