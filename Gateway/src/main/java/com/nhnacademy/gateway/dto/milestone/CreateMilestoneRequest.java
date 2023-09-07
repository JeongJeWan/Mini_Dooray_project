package com.nhnacademy.gateway.dto.milestone;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateMilestoneRequest {

    @NotNull
    private Long projectId;
    @NotBlank(message = "마일스톤 이름을 입력해주세요")
    private final String name;
//    private final LocalDate createdAt;
}
