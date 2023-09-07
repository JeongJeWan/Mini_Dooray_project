package com.nhnacademy.task_api.service.impl;

import com.nhnacademy.task_api.dto.tag.CreateTagDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.tag.TagDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.Tag;
import com.nhnacademy.task_api.exception.ProjectNotFoundException;
import com.nhnacademy.task_api.exception.TagNotFoundException;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.tag.TagRepository;
import com.nhnacademy.task_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    public void createTag(CreateTagDto createTag) {
        Project project = projectRepository.findById(createTag.getProjectId()).orElseThrow(ProjectNotFoundException::new);

        tagRepository.save(new Tag(project, createTag.getName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDto> findTagByProjectId(Long projectId) {

        return tagRepository.findTagsByProject_Id(projectId)
                .stream().map(TagDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TagDto findById(Long id) {

        Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);

        return new TagDto(tag);
    }

    @Override
    public void modifyTag(ModifyTagDto modifyTag) {
        Tag tag = tagRepository.findById(modifyTag.getId()).orElseThrow(TagNotFoundException::new);

        tag.modifyTag(modifyTag.getName());
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(TagNotFoundException::new);

        tagRepository.delete(tag);
    }
}
