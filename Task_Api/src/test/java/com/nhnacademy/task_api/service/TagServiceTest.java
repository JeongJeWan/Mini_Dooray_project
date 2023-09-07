package com.nhnacademy.task_api.service;



import com.nhnacademy.task_api.dto.tag.CreateTagDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.tag.TagDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.entity.Tag;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.tag.TagRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import com.nhnacademy.task_api.service.impl.TagServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("태그 생성")
    void createTag() {

        CreateTagDto createTag = new CreateTagDto();

        ReflectionTestUtils.setField(createTag, "projectId", 1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(mock(Project.class)));
        when(tagRepository.save(any(Tag.class))).thenReturn(mock(Tag.class));

        tagService.createTag(createTag);

        verify(projectRepository, times(1)).findById(anyLong());
        verify(tagRepository, times(1)).save(any(Tag.class));
    }

    @Test
    @DisplayName("프로젝트 해당되는 태그 조회")
    void getFindTagByProject() {
        Long projectId = 1L;

        Project project = new Project();

        ReflectionTestUtils.setField(project, "id", projectId);
        ReflectionTestUtils.setField(project, "projectAdmin", "marco");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.REST);
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        List<Tag> list = new ArrayList<>();

        for (int i=1; i<=10; i++) {
            Tag tag = new Tag();
            ReflectionTestUtils.setField(tag, "project", project);
            ReflectionTestUtils.setField(tag, "name", "tag" +i);
            list.add(tag);
        }

        when(tagRepository.findTagsByProject_Id(projectId)).thenReturn(list);

        List<TagDto> tags = tagService.findTagByProjectId(projectId);

        assertThat(tags).hasSize(10);
    }

    @Test
    @DisplayName("태그 수정")
    void modifyTag() {

        ModifyTagDto modifyTag = new ModifyTagDto();
        ReflectionTestUtils.setField(modifyTag, "id", 1L);
        ReflectionTestUtils.setField(modifyTag, "name", "tag1");

        Tag tag = spy(new Tag());

        when(tagRepository.findById(modifyTag.getId())).thenReturn(Optional.of(tag));

        tagService.modifyTag(modifyTag);

        verify(tagRepository, times(1)).findById(modifyTag.getId());
        verify(tag, times(1)).modifyTag(modifyTag.getName());
        assertThat(tag.getName()).isEqualTo(modifyTag.getName());
    }

    @Test
    @DisplayName("태그 삭제")
    void deleteTag() {
        Long tagId = 1L;

        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        doNothing().when(tagRepository).delete(tag);

        tagService.deleteTag(tagId);

        verify(tagRepository, times(1)).findById(tagId);
        verify(tagRepository, times(1)).delete(tag);
    }
}