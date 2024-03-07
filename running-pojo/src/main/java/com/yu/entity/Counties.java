package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 区县表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Counties implements Serializable {

    private int countiesId;

    private String countiesEncoding; //区县编码

    private String countiesName; //名称

    private String onCityEncoding;  //所属州市编码
}
