package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.tag.ModifyTagDto;
import com.nhnacademy.task_api.dto.task.CreateTaskDto;
import com.nhnacademy.task_api.dto.task.ModifyTaskDto;
import com.nhnacademy.task_api.dto.task.TaskDto;
import com.nhnacademy.task_api.dto.task.TaskListDto;

import java.util.List;

public interface TaskService {

    void createTask(CreateTaskDto createTask);
    List<TaskListDto> findTaskByProjectId(Long id);
    TaskDto findTaskById(Long id);
    void modifyTask(ModifyTaskDto modifyTask);
    void deleteTask(Long id);


}
