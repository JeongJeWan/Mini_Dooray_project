package com.nhnacademy.task_api.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCommentDto {
    private Long id;
    private String content;
}
