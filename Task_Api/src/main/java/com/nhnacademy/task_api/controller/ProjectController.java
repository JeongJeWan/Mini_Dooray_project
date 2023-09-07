package com.nhnacademy.task_api.controller;

import com.nhnacademy.task_api.dto.project.AddProjectMemberDto;
import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import com.nhnacademy.task_api.dto.project.ProjectDto;
import com.nhnacademy.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody CreatedProjectDto createdProject) {

        projectService.createProject(createdProject);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/members")
    public ResponseEntity<Void> addMembers(@RequestBody AddProjectMemberDto addProjectMember) {
        projectService.addMembers(addProjectMember);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> findProject(@PathVariable("id") Long id) {

        ProjectDto project = projectService.findProject(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(project);
    }

    @GetMapping("/members/{userId}")
    public ResponseEntity<List<ProjectDto>> findProjectByUserId(@PathVariable("userId") String userId) {

        List<ProjectDto> projectDtoList = projectService.findProjectByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(projectDtoList);
    }

    @PutMapping("/{id}/terminate")
    public ResponseEntity<Void> changeTerminateProject(@PathVariable("id") Long id) {

        projectService.changeToTerminateProject(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/rest")
    public ResponseEntity<Void> changeRestProject(@PathVariable("id") Long id) {
        projectService.changeToRestProject(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
