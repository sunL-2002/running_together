package com.yu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunTogetherVO implements Serializable {

    private int runId;

    private LocalDateTime runDatetime;  //约跑日期时间

    private String runLocation; //约跑地点
}
