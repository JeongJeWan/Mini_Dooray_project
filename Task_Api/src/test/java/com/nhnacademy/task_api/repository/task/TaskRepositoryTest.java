package com.nhnacademy.task_api.repository.task;

import com.nhnacademy.task_api.entity.Milestone;
import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.entity.Task;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;


    @Test
    void findByProject_Id() {

        Project project = new Project();

        ReflectionTestUtils.setField(project, "name", "ProjectName");
        ReflectionTestUtils.setField(project, "projectAdmin", "admin");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "content", "Project Content");
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        Project savedProject = projectRepository.save(project);
        Milestone milestone = new Milestone();
        ReflectionTestUtils.setField(milestone, "project", savedProject);
        ReflectionTestUtils.setField(milestone, "name", "milestoneName " + 1);
        ReflectionTestUtils.setField(milestone, "createdAt", LocalDate.now());
        milestoneRepository.save(milestone);

        List<Task> taskList = new ArrayList<>();

        for(int i=0; i<10; i++) {
            Task task = new Task();

            ReflectionTestUtils.setField(task, "project", savedProject);
            ReflectionTestUtils.setField(task, "milestone", milestone);
            ReflectionTestUtils.setField(task, "title", "title" + i);
            ReflectionTestUtils.setField(task, "content", "Task Content");
            ReflectionTestUtils.setField(task, "registerId", "registerId" + i);
            ReflectionTestUtils.setField(task, "createdAt", LocalDateTime.now());
            ReflectionTestUtils.setField(task, "deadline", LocalDateTime.now());
            taskList.add(task);
        }


        taskRepository.saveAllAndFlush(taskList);

        List<Task> tasks = taskRepository.findByProject_Id(savedProject.getId());

        tasks.forEach(task ->
                assertThat(task.getProject().getId()).isEqualTo(savedProject.getId()));


    }
}