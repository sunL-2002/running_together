package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 省份表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {

    private int provinceId;

    private String provinceEncoding; //省份编码

    private String  provinceName;  //名称
}
