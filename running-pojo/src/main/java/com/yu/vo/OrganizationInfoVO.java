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
public class OrganizationInfoVO implements Serializable {

    private int groupId;
    private String groupName;
    private String initiator;
    private String groupDescription;
    private String src;
}
