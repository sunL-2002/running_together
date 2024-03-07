package com.yu.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UpdateRunTogetherDTO implements Serializable {
    private int id;

    private String initiator;

    private String openid;

    private String runDatetime;

    private String runLocation;
}
