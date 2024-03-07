package com.yu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 小程序端用户登录
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;
}
