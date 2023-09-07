package com.nhnacademy.task_api.entity;

import com.nhnacademy.task_api.dto.project.CreatedProjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Projects")
@Getter
@NoArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "project_admin", nullable = false)
    private String projectAdmin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @Column(nullable = false)
    private String content;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    public Project(CreatedProjectDto createdProject) {
        this.name = createdProject.getName();
        this.projectAdmin = createdProject.getProjectAdmin();
        this.status = ProjectStatus.ACTIVE;
        this.content = createdProject.getContent();
        this.startDate = LocalDate.now();
    }

    public void changeTerminateProject() {
        this.status = ProjectStatus.TERMINATE;
    }
    public void changeRestProject() {
        this.status = ProjectStatus.REST;
    }

}
