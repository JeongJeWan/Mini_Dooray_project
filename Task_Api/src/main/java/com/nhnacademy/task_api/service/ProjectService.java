package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import com.nhnacademy.task_api.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {

    void createProject(CreatedProjectDto createdProject);

    List<ProjectDto> findProjectByUserId(String userId);

    ProjectDto findProject(Long id);

    void addMembers(AddProjectMemberDto addProjectMember);

    void changeToTerminateProject(Long id);

    public void changeToRestProject(Long id);
}
