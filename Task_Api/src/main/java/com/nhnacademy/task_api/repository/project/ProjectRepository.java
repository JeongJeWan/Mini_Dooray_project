package com.nhnacademy.task_api.repository.project;

import com.nhnacademy.task_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

}
