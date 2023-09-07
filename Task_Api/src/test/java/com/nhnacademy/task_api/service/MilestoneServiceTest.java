package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.milestone.CreateMilestoneDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.milestone.ModifyMilestoneDto;
import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.service.impl.MilestoneServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MilestoneServiceTest {

    @InjectMocks
    private MilestoneServiceImpl milestoneService;

    @Mock
    private MilestoneRepository milestoneRepository;
    @Mock
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("마일스톤 생성")
    void createMilestone() {
        CreateMilestoneDto createMilestone = new CreateMilestoneDto();
        ReflectionTestUtils.setField(createMilestone, "projectId", 1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(mock(Project.class)));
        when(milestoneRepository.save(any(Milestone.class))).thenReturn(null);

        milestoneService.createMilestone(createMilestone);

        verify(projectRepository, times(1)).findById(anyLong());
        verify(milestoneRepository, times(1)).save(any(Milestone.class));
    }

    @Test
    @DisplayName("해당 프로젝트의 마일스톤 목록 조회")
    void testFindMilestoneList() {

        when(milestoneRepository.findMilestoneByProject_Id(1L)).thenReturn(new ArrayList<>());

        List<MilestoneDto> milestoneList = milestoneService.findMilestoneByProjectId(1L);

        verify(milestoneRepository, times(1)).findMilestoneByProject_Id(1L);
        assertThat(milestoneList).isNotNull();
    }

    @Test
    @DisplayName("해당 마일스톤 조회")
    void testFindMilestone() {

        Milestone milestone = spy(new Milestone());

        when(milestoneRepository.findById(1L)).thenReturn(Optional.of(milestone));
        when(milestone.getProject()).thenReturn(mock(Project.class));

        milestoneService.findMilestone(1L);

        verify(milestoneRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("마일스톤 수정")
    void modifyMilestone() {

        ModifyMilestoneDto modifyMilestone = new ModifyMilestoneDto();
        ReflectionTestUtils.setField(modifyMilestone, "id", 1L);

        Milestone milestone = mock(Milestone.class);

        when(milestoneRepository.findById(1L)).thenReturn(Optional.of(milestone));
        doNothing().when(milestone).modifyMilestone(modifyMilestone);

        milestoneService.modifyMilestone(modifyMilestone);

        verify(milestone, times(1)).modifyMilestone(any());
    }

    @Test
    @DisplayName("마일스톤 삭제")
    void deleteMilestone() {

        when(milestoneRepository.findById(1L)).thenReturn(Optional.of(mock(Milestone.class)));
        doNothing().when(milestoneRepository).delete(any(Milestone.class));

        milestoneService.deleteMilestone(1L);

        verify(milestoneRepository, times(1)).delete(any(Milestone.class));
    }
}