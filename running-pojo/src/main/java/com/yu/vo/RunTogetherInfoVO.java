package com.yu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RunTogetherInfoVO implements Serializable {

    private String initiator;

    private String acceptor;

    private LocalDateTime runDatetime;

    private String runLocation;
}
