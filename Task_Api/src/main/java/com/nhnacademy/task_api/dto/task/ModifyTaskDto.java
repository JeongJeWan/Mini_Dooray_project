package com.nhnacademy.task_api.dto.task;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyTaskDto {
    private Long taskId;
    private Long milestoneId;
    private String title;
    private String content;
    private List<Long> tags;
}
