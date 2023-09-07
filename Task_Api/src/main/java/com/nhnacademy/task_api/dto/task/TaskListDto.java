package com.nhnacademy.task_api.dto.task;

import com.nhnacademy.task_api.entity.Task;
import lombok.Getter;

@Getter
public class TaskListDto {
    private final Long id;
    private final String title;

    public TaskListDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
    }
}
