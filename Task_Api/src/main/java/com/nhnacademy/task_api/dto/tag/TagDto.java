package com.nhnacademy.task_api.dto.tag;

import com.nhnacademy.task_api.entity.Tag;
import lombok.Getter;

@Getter
public class TagDto {
    private final Long id;
    private final String name;
    public TagDto(Tag tag) {
        this.id= tag.getId();
        this.name= tag.getName();
    }
}
