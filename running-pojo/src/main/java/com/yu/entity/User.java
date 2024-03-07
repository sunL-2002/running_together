package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private int userId;

    private String username;

    private String avatar;  //头像

    private String phoneNumber;  //电话

    private String openid;  //小程序用户标识
}
