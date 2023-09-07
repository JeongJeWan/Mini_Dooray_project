package com.nhnacademy.task_api.repository.tasktag;

import com.nhnacademy.task_api.entity.*;
import com.nhnacademy.task_api.repository.milestone.MilestoneRepository;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import com.nhnacademy.task_api.repository.tag.TagRepository;
import com.nhnacademy.task_api.repository.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class TaskTagRepositoryTest {

    @Autowired
    private TaskTagRepository taskTagRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;

    @Test
    void findByTaskId() {

        Project project = new Project();

        ReflectionTestUtils.setField(project, "name", "name");
        ReflectionTestUtils.setField(project, "projectAdmin", "admin");
        ReflectionTestUtils.setField(project, "content", "content");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        Project savedProject = projectRepository.save(project);

        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tag tag = new Tag();
            ReflectionTestUtils.setField(tag, "project", savedProject);
            ReflectionTestUtils.setField(tag, "name", "tag name" + i);
            tagList.add(tag);
        }

        Milestone milestone = new Milestone();
        ReflectionTestUtils.setField(milestone, "project", savedProject);
        ReflectionTestUtils.setField(milestone, "name", "milestoneName " + 1);
        ReflectionTestUtils.setField(milestone, "createdAt", LocalDate.now());
        milestoneRepository.save(milestone);

        List<Tag> savedTag = tagRepository.saveAll(tagList);

        Task task = new Task();
        ReflectionTestUtils.setField(task, "project", savedProject);
        ReflectionTestUtils.setField(task, "milestone", milestone);
        ReflectionTestUtils.setField(task, "title", "title");
        ReflectionTestUtils.setField(task, "content", "Task Content");
        ReflectionTestUtils.setField(task, "registerId", "registerId");
        ReflectionTestUtils.setField(task, "createdAt", LocalDateTime.now());
        ReflectionTestUtils.setField(task, "deadline", LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        List<TaskTag> collect = savedTag.stream()
                .peek(t -> System.out.println(t.getId()))
                .map(tag -> new TaskTag(savedTask, tag))
                .collect(toList());

        taskTagRepository.saveAll(collect);

        List<TaskTag> taskTags = taskTagRepository.findByTaskId(savedTask.getId());

        taskTags.forEach(tt -> assertThat(tt.getTask().getId()).isEqualTo(savedTask.getId()));
    }

}