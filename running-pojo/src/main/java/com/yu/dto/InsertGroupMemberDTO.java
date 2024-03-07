package com.yu.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class InsertGroupMemberDTO implements Serializable {

    private int groupId;
    private String userOpenid;
}
