package com.yu.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取图片返回的数据格式
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "获取图片返回的数据格式")
public class ImagesVO implements Serializable {
    //主键值
    private int id;
    //路径
    private String url;
}
