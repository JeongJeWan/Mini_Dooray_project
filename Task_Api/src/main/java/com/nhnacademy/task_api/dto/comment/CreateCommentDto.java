package com.nhnacademy.task_api.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentDto {
    private Long taskId;
    private String registerId;
    private String content;
}
