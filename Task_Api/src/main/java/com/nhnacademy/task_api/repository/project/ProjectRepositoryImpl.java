package com.nhnacademy.task_api.repository.project;

import com.nhnacademy.task_api.dto.project.ProjectDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.QProject;
import com.nhnacademy.task_api.entity.QProjectMember;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    public ProjectRepositoryImpl() {
        super(Project.class);
    }

    @Override
    public List<ProjectDto> findProjectByUserId(String userId) {
        QProject project = QProject.project;
        QProjectMember projectMember = QProjectMember.projectMember;

        return from(projectMember)
                .innerJoin(projectMember.project, project)
                .select(Projections.constructor(ProjectDto.class,
                        project))
                .where(projectMember.userId.eq(userId)).fetch();
    }
}
