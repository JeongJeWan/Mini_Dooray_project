package com.nhnacademy.gateway.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class AddProjectMemeberRequest {

    private Long id;
    private List<MemberInfo> memberInfoList = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public static class MemberInfo {
        private Long memberId;
        private String userId;
    }
}
