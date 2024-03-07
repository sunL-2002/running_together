package com.yu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 挑战PK表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChallengePk implements Serializable {

    private int challengeId;

    private int initiatorId; //发起人id

    private int acceptorId; //接受人id

    private String src;//图片路径

    private String challengeContent; //挑战内容

    private String tag;//挑战标签

    private byte completionStatus; //完成状态 0未完成，1已完成
}
