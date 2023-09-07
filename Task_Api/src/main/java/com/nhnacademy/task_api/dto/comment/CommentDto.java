package com.nhnacademy.task_api.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.task_api.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentDto {
    private final Long id;
    private final String registerId;
    private final String content;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private final LocalDateTime modifiedAt;
    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.registerId = comment.getRegisterId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
