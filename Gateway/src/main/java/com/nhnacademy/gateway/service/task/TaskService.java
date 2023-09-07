package com.nhnacademy.gateway.service.task;

import com.nhnacademy.gateway.adapter.TaskAdapter;
import com.nhnacademy.gateway.dto.task.CreateTaskRequest;
import com.nhnacademy.gateway.dto.task.ModifyTaskRequest;
import com.nhnacademy.gateway.dto.task.TaskDto;
import com.nhnacademy.gateway.dto.task.TaskListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskAdapter taskAdapter;

    public void createTask(final CreateTaskRequest createTaskRequest) {
        taskAdapter.createTask(createTaskRequest);
    }

    public List<TaskListResponse> findTasksByProjectId(final Long projectId) {
        return taskAdapter.findTaskList(projectId);
    }

    public TaskDto findTask(final Long id) {
        return taskAdapter.findTask(id);
    }

    public void modifyTask(final ModifyTaskRequest modifyTaskRequest) {
        taskAdapter.modifyTask(modifyTaskRequest);
    }

    public void deleteTask(final Long id) {
        taskAdapter.deleteTask(id);
    }
}
