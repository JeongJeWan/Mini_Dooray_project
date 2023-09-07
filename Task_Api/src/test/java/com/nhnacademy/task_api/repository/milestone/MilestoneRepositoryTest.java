package com.nhnacademy.task_api.repository.milestone;


import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MilestoneRepositoryTest {

    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void findMilestoneByProject_Id() {

        Project project = new Project();

        ReflectionTestUtils.setField(project, "id", 1L);
        ReflectionTestUtils.setField(project, "name", "ProjectName");
        ReflectionTestUtils.setField(project, "projectAdmin", "admin");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "content", "Project Content");
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        Project savedProject = projectRepository.save(project);

        List<Milestone> milestoneList = new ArrayList<>();

        for(int i = 1; i < 11; i++) {
            Milestone milestone = new Milestone();
            ReflectionTestUtils.setField(milestone, "id", (long) i);
            ReflectionTestUtils.setField(milestone, "project", savedProject);
            ReflectionTestUtils.setField(milestone, "name", "milestoneName " + i);
            ReflectionTestUtils.setField(milestone, "createdAt", LocalDate.now());
            milestoneList.add(milestone);
        }

        milestoneRepository.saveAll(milestoneList);

        List<Milestone> milestones = milestoneRepository.findMilestoneByProject_Id(savedProject.getId());

        milestones.forEach(m -> assertThat(m.getProject().getId()).isEqualTo(savedProject.getId()));
    }
}