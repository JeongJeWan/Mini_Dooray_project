package com.nhnacademy.task_api.service.impl;

import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import com.nhnacademy.task_api.dto.project.ProjectDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectMember;
import com.nhnacademy.task_api.exception.ProjectNotFoundException;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.projectmember.ProjectMemberRepository;
import com.nhnacademy.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public void createProject(CreatedProjectDto createdProject) {
        Project createProject = projectRepository.save(new Project(createdProject));

        ProjectMember projectAdmin = new ProjectMember(createProject, createProject.getId(), createdProject.getProjectAdmin());
        projectMemberRepository.save(projectAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findProjectByUserId(String userId) {

        return new ArrayList<>(projectRepository.findProjectByUserId(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDto findProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);

        return new ProjectDto(project);
    }

    @Override
    public void addMembers(AddProjectMemberDto addProjectMember) {
        Project project = projectRepository.findById(addProjectMember.getId()).orElseThrow(ProjectNotFoundException::new);

        List<ProjectMember> projectMembers = addProjectMember
                .getMemberInfoList()
                .stream()
                .map(info -> new ProjectMember(project, info))
                .collect(Collectors.toList());

        projectMemberRepository.saveAll(projectMembers);

    }

    @Override
    public void changeToTerminateProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);

        project.changeTerminateProject();
    }

    @Override
    public void changeToRestProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);

        project.changeRestProject();
    }
}
