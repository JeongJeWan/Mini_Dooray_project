package com.nhnacademy.gateway.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequest {

    private Long taskId;
    private String registerId;
    private String content;
}
