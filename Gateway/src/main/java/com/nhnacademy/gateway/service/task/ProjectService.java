package com.nhnacademy.gateway.service.task;

import com.nhnacademy.gateway.adapter.ProjectAdapter;
import com.nhnacademy.gateway.dto.project.AddProjectMemeberRequest;
import com.nhnacademy.gateway.dto.project.CreateProjectRequest;
import com.nhnacademy.gateway.dto.project.ProjectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectAdapter projectAdapter;

    public void createProject(final CreateProjectRequest createProjectRequest) {
        projectAdapter.createProject(createProjectRequest);
    }

    public List<ProjectDto> findProjectByUserId(final String userId) {
        return projectAdapter.findProjectList(userId);
    }

    public ProjectDto findProject(final Long id) {
        return projectAdapter.findProject(id);
    }

    public void addMembers(final AddProjectMemeberRequest addProjectMemeberRequest) {
        projectAdapter.addMembers(addProjectMemeberRequest);
    }

    public void makeTerminateProject(final Long id) {
        projectAdapter.makeTerminateProject(id);
    }

    public void makeRestProject(final Long id) {
        projectAdapter.makeRestProject(id);
    }
}
