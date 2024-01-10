package com.sandy.advancedSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class AdminMemberRequestDto {

    private String adminUsername;
    private String adminPw;
    private Long departmentId;
    private String phone;
    private String userUsername;
    private String userPw;

}
