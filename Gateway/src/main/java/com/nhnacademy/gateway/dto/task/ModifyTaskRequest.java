package com.nhnacademy.gateway.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class ModifyTaskRequest {

    @NotNull
    private Long taskId;
    private Long milestoneId;
    @NotBlank
    private String title;

    @NotBlank
    private String content;
    private List<Long> tags;
}
