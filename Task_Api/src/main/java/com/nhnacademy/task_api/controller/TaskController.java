package com.nhnacademy.task_api.controller;

import com.nhnacademy.task_api.dto.task.CreateTaskDto;
import com.nhnacademy.task_api.dto.task.ModifyTaskDto;
import com.nhnacademy.task_api.dto.task.TaskDto;
import com.nhnacademy.task_api.dto.task.TaskListDto;
import com.nhnacademy.task_api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskDto createTask) {
        taskService.createTask(createTask);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findTask(@PathVariable("id") Long id) {
        TaskDto task = taskService.findTaskById(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(task);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskListDto>> findTaskList(@PathVariable("projectId") Long projectId) {

        List<TaskListDto> taskListDtoList = taskService.findTaskByProjectId(projectId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(taskListDtoList);
    }

    @PutMapping
    public ResponseEntity<Void> modifyTask(@RequestBody ModifyTaskDto modifyTask) {

        taskService.modifyTask(modifyTask);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
