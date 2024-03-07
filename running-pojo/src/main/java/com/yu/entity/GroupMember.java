package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 跑团成员表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMember implements Serializable {

    private int memberId;

    private String userOpenid;

    private int groupId; //所属跑团ID
}
