package com.nhnacademy.task_api.service;



import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectMember;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.task_api.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @Test
    @DisplayName("프로젝트 생성")
    void createProject() {

        CreatedProjectDto createdProject = new CreatedProjectDto();

        ReflectionTestUtils.setField(createdProject, "projectAdmin", "marco");
        ReflectionTestUtils.setField(createdProject, "name", "Project");

        when(projectRepository.save(any(Project.class))).thenReturn(mock(Project.class));
        when(projectMemberRepository.save(any(ProjectMember.class))).thenReturn(mock(ProjectMember.class));

        projectService.createProject(createdProject);

        verify(projectRepository, times(1)).save(any(Project.class));
        verify(projectMemberRepository, times(1)).save(any(ProjectMember.class));
    }

    @Test
    @DisplayName("프로젝트 멤버 추가")
    void addProjectMember() {

        AddProjectMemberDto addProjectMember = new AddProjectMemberDto();

        ReflectionTestUtils.setField(addProjectMember, "id", 1L);

        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(mock(Project.class)));
        when(projectMemberRepository.saveAll(anyCollection())).thenReturn(null);

        projectService.addMembers(addProjectMember);

        verify(projectRepository, times(1)).findById(anyLong());
        verify(projectMemberRepository, times(1)).saveAll(anyCollection());
    }

    @Test
    @DisplayName("프로젝트 종료")
    void testChangeTerminateProject() {
        Project project = spy(new Project());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.changeToTerminateProject(1L);

        verify(project, times(1)).changeTerminateProject();
        assertThat(project.getStatus()).isEqualTo(ProjectStatus.TERMINATE);
    }

    @Test
    @DisplayName("프로젝트 휴면")
    void testChangeRestProject() {
        Project project = spy(new Project());

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.changeToRestProject(1L);

        verify(project, times(1)).changeRestProject();
        assertThat(project.getStatus()).isEqualTo(ProjectStatus.REST);
    }

}