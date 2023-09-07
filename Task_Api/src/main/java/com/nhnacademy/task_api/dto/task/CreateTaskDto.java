package com.nhnacademy.task_api.dto.task;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTaskDto {
    private Long projectId;
    private Long milestoneId;
    private String title;
    private String content;
    private String registerId;
    private List<Long> tags = new ArrayList<>();
}
