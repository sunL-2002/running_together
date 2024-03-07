package com.yu.dto;

import lombok.Data;

@Data
public class EventPageQueryDTO {

    private int page;

    private int pageSize;

    private String eventName;
}
