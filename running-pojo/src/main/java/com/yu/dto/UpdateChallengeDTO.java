package com.yu.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateChallengeDTO implements Serializable {
    private int challengeId;
    private String initiator;
    private String openid;
    private String src;
    private String challengeContent;
    private String tag;
}
