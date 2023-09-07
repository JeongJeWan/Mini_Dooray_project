package com.nhnacademy.task_api.repository.project;

import com.nhnacademy.task_api.dto.project.ProjectDto;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectMember;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.repository.projectmember.ProjectMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Test
    @DisplayName("userid 로 조회")
    void findProjectByUserId() {
        Long memberId = 1L;
        String userId = "marco";

        for(int i=0; i<10; i++) {
            Project project = new Project();

            ReflectionTestUtils.setField(project, "name", "name"+i);
            ReflectionTestUtils.setField(project, "projectAdmin", "admin");
            ReflectionTestUtils.setField(project, "content", "content");
            ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
            ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

            Project saveProject = projectRepository.save(project);

            ProjectMember projectMember = new ProjectMember(saveProject, memberId, userId);
            projectMemberRepository.save(projectMember);
        }

        List<ProjectDto> projects = projectRepository.findProjectByUserId(userId);

        projects.stream().forEach(System.out::println);

        assertThat(projects.size()).isEqualTo(10);
    }
}