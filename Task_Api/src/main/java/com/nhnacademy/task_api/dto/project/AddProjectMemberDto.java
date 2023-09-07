package com.nhnacademy.task_api.dto.project;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddProjectMemberDto {

    @NotNull
    private Long id;

    @NotEmpty
    private List<MemberInfo> memberInfoList = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    public static class MemberInfo {
        private Long memberId;
        private String userId;
    }
}
