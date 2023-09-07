package com.nhnacademy.gateway.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateTaskRequest {

    @NotNull
    private Long projectId;
    private Long milestoneId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String registerId;
    private List<Long> tags;
}
