package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.tag.CreateTagDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.tag.TagDto;

import java.util.List;

public interface TagService {

    void createTag(CreateTagDto createTag);
    List<TagDto> findTagByProjectId(Long projectId);
    TagDto findById(Long id);
    void modifyTag(ModifyTagDto modifyTag);
    void deleteTag(Long id);
}
