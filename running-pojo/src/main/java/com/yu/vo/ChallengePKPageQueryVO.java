package com.yu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePKPageQueryVO implements Serializable {

    //发起人姓名
    private String initiator;

    //接收人姓名
    private String acceptor;

    private String challengeContent;

    private int completionStatus;

    private String tag;
}
