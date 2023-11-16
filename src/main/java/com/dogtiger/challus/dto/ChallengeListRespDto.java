package com.dogtiger.challus.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChallengeListRespDto {
    private int challengeId;
    private int userId;
    private String challengeName;
    private int layout;
    private String introduction;
    private String isOpen;
    private String isApplicable;
    private String startDate;
    private String endDate;
    private String categoryName;
    private String name;
    private int likeCount;
    private int isDeadline;
    private int isHidden;
}
