package com.nhnacademy.task_api.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.task_api.entity.Project;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ProjectDto {

    private Long id;
    private String name;
    private String status;
    private String projectAdmin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.status = project.getStatus().getStatus();
        this.projectAdmin = project.getProjectAdmin();
        this.startDate = project.getStartDate();
    }
}
