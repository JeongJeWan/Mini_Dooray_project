package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.task.CreateTaskDto;
import com.nhnacademy.task_api.dto.task.ModifyTaskDto;
import com.nhnacademy.task_api.dto.task.TaskDto;
import com.nhnacademy.task_api.dto.task.TaskListDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.repository.comment.CommentRepository;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.task_api.repository.tag.TagRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import com.nhnacademy.task_api.repository.tasktag.TaskTagRepository;
import com.nhnacademy.task_api.service.impl.TaskServiceImpl;
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

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private MilestoneRepository milestoneRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TaskTagRepository taskTagRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;
    @Mock
    private CommentRepository commentRepository;

    @Test
    @DisplayName("업무 생성")
    void createTask() {

        CreateTaskDto createTask = new CreateTaskDto();

        ReflectionTestUtils.setField(createTask, "projectId", 1L);
        ReflectionTestUtils.setField(createTask, "milestoneId", 0L);
        ReflectionTestUtils.setField(createTask, "tags", new ArrayList<>());

        Task task = mock(Task.class);

        when(projectRepository.findById(createTask.getProjectId())).thenReturn(Optional.of(mock(Project.class)));
        lenient().when(tagRepository.findById(anyLong())).thenReturn(null);
        lenient().when(projectMemberRepository.existsById_memberId(anyLong())).thenReturn(false);

        lenient().when(task.getId()).thenReturn(1L);

        taskService.createTask(createTask);
    }

    @Test
    @DisplayName("업무 조회")
    void getFindTask() {

        Long taskId = 1L;
        Task task = spy(new Task());

        when(task.getId()).thenReturn(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskTagRepository.findByTaskId(anyLong())).thenReturn(new ArrayList<>());
        when(commentRepository.findByTaskId(task.getId())).thenReturn(new ArrayList<>());

        TaskDto actual = taskService.findTaskById(taskId);

        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("프로젝트에 해당되는 업무 목록 조회")
    void testFindTaskByProject() {

        Long projectId = 1L;

        Project project = new Project();

        ReflectionTestUtils.setField(project, "id", projectId);
        ReflectionTestUtils.setField(project, "projectAdmin", "marco");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "name", "Project");
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            ReflectionTestUtils.setField(task, "project", project);
            ReflectionTestUtils.setField(task, "title", "title" + i);
            ReflectionTestUtils.setField(task, "content", "content");
            ReflectionTestUtils.setField(task, "registerId", "registrantName");
            ReflectionTestUtils.setField(task, "createdAt", LocalDateTime.now());
            taskList.add(task);
        }

        when(taskRepository.findByProject_Id(projectId)).thenReturn(taskList);

        List<TaskListDto> taskListDtoList = taskService.findTaskByProjectId(projectId);

        assertThat(taskListDtoList).hasSize(10);
    }

    @Test
    @DisplayName("업무 수정")
    void testModifyTask() {

        Long taskId = 1L;
        Task task = spy(new Task());
        ReflectionTestUtils.setField(task, "id", taskId);

        ModifyTaskDto modifyTask = new ModifyTaskDto();

        ReflectionTestUtils.setField(modifyTask, "taskId", taskId);
        ReflectionTestUtils.setField(modifyTask, "milestoneId", 0L);
        ReflectionTestUtils.setField(modifyTask, "title", "title1");
        ReflectionTestUtils.setField(modifyTask, "content", "content1");
        ReflectionTestUtils.setField(modifyTask, "tags", new ArrayList<>());

        when(taskRepository.findById(modifyTask.getTaskId())).thenReturn(Optional.of(task));

        doNothing().when(taskTagRepository).deleteAll(anyCollection());
        when(taskTagRepository.findByTaskId(task.getId())).thenReturn(new ArrayList<>());
        when(taskTagRepository.saveAll(anyCollection())).thenReturn(new ArrayList<>());

        taskService.modifyTask(modifyTask);

        verify(task, times(1)).modifyTask(any(), any(ModifyTaskDto.class));
    }

    @Test
    @DisplayName("업무 삭제")
    void testDeleteTask() {

        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mock(Task.class)));
        doNothing().when(taskRepository).delete(any(Task.class));

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).delete(any(Task.class));
    }
}