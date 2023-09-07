package com.nhnacademy.gateway.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCommentRequest {

    private Long id;
    private String content;
}
