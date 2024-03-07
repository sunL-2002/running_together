package com.yu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String groupName;
}
