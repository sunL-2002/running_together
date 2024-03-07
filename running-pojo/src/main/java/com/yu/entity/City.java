package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 州市表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    private int cityId;

    private String cityEncoding; //州市编码

    private String cityName; //名称

    private String onProvinceEncoding; //所属省份编码
}
