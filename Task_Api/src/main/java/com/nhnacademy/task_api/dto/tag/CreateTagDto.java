package com.nhnacademy.task_api.dto.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateTagDto {
    private Long projectId;
    private String name;
}
