package com.nhnacademy.task_api.repository.comment;

import com.nhnacademy.task_api.entity.*;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MilestoneRepository milestoneRepository;


    @Test
    void findByTaskId() {
        Comment comment = new Comment();
        Task task = new Task();
        Project project = new Project();
        Milestone milestone = new Milestone();

        ReflectionTestUtils.setField(project, "name", "ProjectName");
        ReflectionTestUtils.setField(project, "projectAdmin", "admin");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "content", "Project Content");
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        projectRepository.save(project);

        ReflectionTestUtils.setField(milestone, "project", project);
        ReflectionTestUtils.setField(milestone, "name", "milestoneName " + 1);
        ReflectionTestUtils.setField(milestone, "createdAt", LocalDate.now());
        milestoneRepository.save(milestone);

        ReflectionTestUtils.setField(task, "project", project);
        ReflectionTestUtils.setField(task, "milestone", milestone);
        ReflectionTestUtils.setField(task, "title", "title" );
        ReflectionTestUtils.setField(task, "content", "Task Content");
        ReflectionTestUtils.setField(task, "registerId", "registerId");
        ReflectionTestUtils.setField(task, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(task, "deadline", LocalDateTime.now());

        taskRepository.save(task);


        ReflectionTestUtils.setField(comment, "task", task);
        ReflectionTestUtils.setField(comment, "registerId", "12345");
        ReflectionTestUtils.setField(comment, "content", "comment content");
        ReflectionTestUtils.setField(comment, "createdAt", LocalDateTime.now());

        commentRepository.save(comment);

        List<Comment> actual = commentRepository.findByTaskId(task.getId());
        assertThat(actual).isNotEmpty();


    }
}