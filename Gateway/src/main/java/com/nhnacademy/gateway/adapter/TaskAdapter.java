package com.nhnacademy.gateway.adapter;

import com.nhnacademy.gateway.dto.task.CreateTaskRequest;
import com.nhnacademy.gateway.dto.task.ModifyTaskRequest;
import com.nhnacademy.gateway.dto.task.TaskDto;
import com.nhnacademy.gateway.dto.task.TaskListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.nhnacademy.gateway.adapter.AdapterTemplate.*;
import static org.springframework.http.HttpMethod.GET;

@Component
@RequiredArgsConstructor
public class TaskAdapter {

    private static final String TASKS = "/task/tasks";
    private static final String REQUEST_URL = BASE_URL + TASKS;
    private final RestTemplate restTemplate;

    public void createTask(CreateTaskRequest creataRequest) {

        create(restTemplate, TASKS, creataRequest);
    }

    public TaskDto findTask(Long id) {

        final String PATH = "/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<TaskDto> exchange = restTemplate.exchange(REQUEST_URL + PATH, GET, httpEntity, new ParameterizedTypeReference<>() {});

        verifyCode(exchange.getStatusCode());

        return exchange.getBody();
    }

    public List<TaskListResponse> findTaskList(Long projectId) {

        AdapterTemplate<List<TaskListResponse>> template = AdapterTemplate.of();
        return template.findAll(restTemplate, TASKS + "/project/" + projectId);
    }

    public void modifyTask(ModifyTaskRequest modifyRequest) {

        modify(restTemplate, TASKS,modifyRequest);
    }

    public void deleteTask(Long id) {

        delete(restTemplate, TASKS, id);
    }
}
