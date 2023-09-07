package com.nhnacademy.gateway.adapter;

import static com.nhnacademy.gateway.adapter.AdapterTemplate.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

import com.nhnacademy.gateway.dto.project.AddProjectMemeberRequest;
import com.nhnacademy.gateway.dto.project.CreateProjectRequest;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectAdapter {

    private static final String REST = "rest";
    private static final String TERMINATE = "terminate";
    private static final String PROJECTS = "/task/projects";
    private static final String REQUEST_URL = BASE_URL + PROJECTS;

    private final RestTemplate restTemplate;

    public void createProject(final CreateProjectRequest createProject) {

        create(restTemplate, PROJECTS, createProject);
    }

    public void addMembers(final AddProjectMemeberRequest addProjectMemeberRequest) {

        final String PATH = PROJECTS + "/members";

        create(restTemplate, PATH, addProjectMemeberRequest);
    }

    public List<ProjectDto> findProjectList(final String userId) {

        final String PATH = PROJECTS + "/members/" + userId;

        AdapterTemplate<List<ProjectDto>> template = AdapterTemplate.of();

        return template.findAll(restTemplate, PATH);
    }

    public ProjectDto findProject(final Long id) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<ProjectDto> exchange = restTemplate.exchange(REQUEST_URL + "/" + id, GET, httpEntity, ProjectDto.class);

        verifyCode(exchange.getStatusCode());

        return exchange.getBody();
    }

    public void makeRestProject(final Long id) {

        projectStatusChange(REST, id);
    }

    public void makeTerminateProject(final Long id) {

        projectStatusChange(TERMINATE, id);
    }


    private void projectStatusChange(final String status, final Long id) {
        final String PATH = "/" + id + "/" + status;

        HttpEntity<Void> httpEntity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<Void> exchange = restTemplate.exchange(REQUEST_URL + PATH, PUT, httpEntity, new ParameterizedTypeReference<Void>() {});

        verifyCode(exchange.getStatusCode());
    }
}
