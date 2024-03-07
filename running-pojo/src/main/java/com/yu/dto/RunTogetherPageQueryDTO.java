package com.yu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RunTogetherPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    //发起人姓名
    private String initiator;

    //接收人姓名
    private String acceptor;

    private int initiatorId;
    private int acceptorId;
}
