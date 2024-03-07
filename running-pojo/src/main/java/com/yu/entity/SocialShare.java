package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * 社交分享表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialShare implements Serializable {

    private int shareId;

    private int userId;

    private String shareContent;  //分享内容

    private LocalDate shareDate;  //分享日期
}
