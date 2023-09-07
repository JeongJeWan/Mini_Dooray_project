package com.nhnacademy.gateway.service.task;


import com.nhnacademy.gateway.adapter.TagAdapter;
import com.nhnacademy.gateway.dto.tag.CreateTagRequest;
import com.nhnacademy.gateway.dto.tag.ModifyTagRequest;
import com.nhnacademy.gateway.dto.tag.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagAdapter tagAdapter;

    public void createTag(CreateTagRequest createTagRequest) {
        tagAdapter.createTag(createTagRequest);
    }

    public List<TagDto> findTagsByProjectId(Long projectId) {
        return tagAdapter.findTagsByProjectId(projectId);
    }

    public TagDto findTag(Long id) {
        return tagAdapter.findById(id);
    }

    public void modifyTag(ModifyTagRequest modifyTagRequest) {

        tagAdapter.modifyTag(modifyTagRequest);
    }

    public void deleteTag(Long id) {

        tagAdapter.deleteTag(id);
    }
}
