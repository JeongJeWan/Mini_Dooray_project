package com.nhnacademy.task_api.repository.project;

import com.nhnacademy.task_api.dto.project.ProjectDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectRepositoryCustom {
    List<ProjectDto> findProjectByUserId(String userId);
}
