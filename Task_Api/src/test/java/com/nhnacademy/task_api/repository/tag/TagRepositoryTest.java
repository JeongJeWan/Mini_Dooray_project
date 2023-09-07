package com.nhnacademy.task_api.repository.tag;

import com.nhnacademy.task_api.entity.Project;
import com.nhnacademy.task_api.entity.ProjectStatus;
import com.nhnacademy.task_api.entity.Tag;
import com.nhnacademy.task_api.repository.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void findTagsByProject_Id() {
        Project project = new Project();

        ReflectionTestUtils.setField(project, "name", "ProjectName");
        ReflectionTestUtils.setField(project, "projectAdmin", "admin");
        ReflectionTestUtils.setField(project, "status", ProjectStatus.ACTIVE);
        ReflectionTestUtils.setField(project, "content", "Project Content");
        ReflectionTestUtils.setField(project, "startDate", LocalDate.now());

        Project savedProject = projectRepository.save(project);

        List<Tag> list = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Tag tag = new Tag();
            ReflectionTestUtils.setField(tag, "project", savedProject);
            ReflectionTestUtils.setField(tag,"name", "tag" + i);
            list.add(tag);
        }

        tagRepository.saveAll(list);

        List<Tag> tag = tagRepository.findTagsByProject_Id(savedProject.getId());

        tag.forEach(t -> assertThat(t.getProject().getId()).isEqualTo(project.getId()));
        assertThat(tag).hasSize(10);


    }
}