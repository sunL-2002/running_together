package com.yu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePlanVO implements Serializable {

    private int planId;

    private LocalDate startDate; //开始日期

    private LocalDate endDate; //结束日期

    private String planGoal; //计划目标
}
