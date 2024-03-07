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
public class ChallengeInfoVO implements Serializable {

    private String initiator;
    private String acceptor;
    private String challengeContent;
    private String src;
    private String tag;
}
