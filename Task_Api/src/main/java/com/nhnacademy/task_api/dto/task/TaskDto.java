package com.nhnacademy.task_api.dto.task;

import com.nhnacademy.task_api.dto.comment.CommentDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.tag.TagDto;
import com.nhnacademy.task_api.entity.Task;
import java.util.List;
import lombok.Getter;

@Getter
public class TaskDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String registerId;
    private final MilestoneDto milestone;
    private final List<TagDto> tags;
    private final List<CommentDto> comments;

    public TaskDto(Task task,MilestoneDto milestone,List<TagDto> tags,List<CommentDto> comments) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.content = task.getContent();
        this.registerId = task.getRegisterId();
        this.milestone = milestone;
        this.tags = tags;
        this.comments = comments;
    }
}
