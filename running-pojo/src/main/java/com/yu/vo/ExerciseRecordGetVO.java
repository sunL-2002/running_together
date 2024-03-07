package com.yu.vo;

import com.yu.dto.PlaylineDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "请求运动记录信息返回的数据格式")
public class ExerciseRecordGetVO  implements Serializable {

    @ApiModelProperty("主键值")
    private int recordId;

    @ApiModelProperty("运动日期")
    private LocalDate exerciseDate;

    @ApiModelProperty("跑步路线")
    private List<PlaylineDTO> runningRoute;

    @ApiModelProperty("距离")
    private float distance;

    @ApiModelProperty("运动时间")
    private String time;
}
