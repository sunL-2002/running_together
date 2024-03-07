package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 状态信息表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusInfo implements Serializable {

    private int infoId;

    private String statusName;  //名称

    private String statusSymbol;  //状态符号
}
