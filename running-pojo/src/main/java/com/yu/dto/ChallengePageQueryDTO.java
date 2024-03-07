package com.yu.dto;

import lombok.Data;

@Data
public class ChallengePageQueryDTO {

    //发起人姓名
    private String initiator;

    //接收人姓名
    private String acceptor;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;

    private int initiatorId;
    private int acceptorId;

}
