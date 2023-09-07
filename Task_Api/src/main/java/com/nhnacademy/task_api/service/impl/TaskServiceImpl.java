package com.nhnacademy.task_api.service.impl;

import com.nhnacademy.task_api.dto.comment.CommentDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.tag.TagDto;
import com.nhnacademy.task_api.dto.task.CreateTaskDto;
import com.nhnacademy.task_api.dto.task.ModifyTaskDto;
import com.nhnacademy.task_api.dto.task.TaskDto;
import com.nhnacademy.task_api.dto.task.TaskListDto;
import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.entity.TaskTag;
import com.nhnacademy.task_api.exception.MilestoneNotFoundException;
import com.nhnacademy.task_api.exception.ProjectNotFoundException;
import com.nhnacademy.task_api.exception.TaskNotFoundException;
import com.nhnacademy.task_api.repository.comment.CommentRepository;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.task_api.repository.tag.TagRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import com.nhnacademy.task_api.repository.tasktag.TaskTagRepository;
import com.nhnacademy.task_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final CommentRepository commentRepository;

    @Override
    public void createTask(CreateTaskDto createTask) {
        Project project = projectRepository.findById(createTask.getProjectId()).orElseThrow(ProjectNotFoundException::new);
        Milestone milestone = milestoneRepository.findById(Optional.ofNullable(createTask.getMilestoneId()).orElse(0L)).orElse(null);

        Task task = new Task(project, milestone, createTask);

        Task savedTest = taskRepository.save(task);


        List<TaskTag> taskTagList = Optional.ofNullable(createTask.getTags())
                .orElse(new ArrayList<>())
                .stream()
                .map(t -> tagRepository.findById(t).orElse(null))
                .filter(Objects::nonNull)
                .map(tag -> new TaskTag(savedTest, tag))
                .collect(Collectors.toList());

        if (!taskTagList.isEmpty()) {
            taskTagRepository.saveAll(taskTagList);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskListDto> findTaskByProjectId(Long id) {
        return taskRepository.findByProject_Id(id)
                .stream()
                .map(TaskListDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDto findTaskById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        Milestone milestone = task.getMilestone();

        MilestoneDto milestoneDto = null;

        if(Objects.nonNull(milestone)) {
            milestoneDto = new MilestoneDto(milestone);
        }

        List<TagDto> tags = taskTagRepository.findByTaskId(task.getId())
                .stream()
                .map(taskTag -> tagRepository.findById(taskTag.getTag().getId()).orElse(null))
                .filter(Objects::nonNull)
                .map(TagDto::new)
                .collect(Collectors.toList());

        List<CommentDto> comments = commentRepository.findByTaskId(task.getId())
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        return new TaskDto(task, milestoneDto, tags, comments);
    }

    @Override
    public void modifyTask(ModifyTaskDto modifyTask) {

        Task task = taskRepository.findById(modifyTask.getTaskId())
                .orElseThrow(TaskNotFoundException::new);

        Milestone milestone = milestoneRepository.findById(Optional.ofNullable(modifyTask.getMilestoneId()).orElse(0L)).orElse(null);

        task.modifyTask(milestone, modifyTask);
        taskRepository.flush();

        // TODO: 1 검증 필요
        List<TaskTag> taskTags = taskTagRepository.findByTaskId(task.getId());
        taskTagRepository.deleteAll(taskTags);

        List<TaskTag> taskTagList = Optional.ofNullable(modifyTask.getTags())
                .orElse(new ArrayList<>())
                .stream()
                .map(t -> tagRepository.findById(t).orElse(null))
                .filter(Objects::nonNull)
                .map(tag -> new TaskTag(task, tag))
                .collect(Collectors.toList());
        taskTagRepository.saveAll(taskTagList);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        List<TaskTag> taskTags = taskTagRepository.findByTaskId(id);
        taskTagRepository.deleteAll(taskTags);

        taskRepository.delete(task);
    }
}
