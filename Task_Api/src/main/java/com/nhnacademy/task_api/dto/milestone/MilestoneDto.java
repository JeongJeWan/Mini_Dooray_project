package com.nhnacademy.task_api.dto.milestone;

import com.nhnacademy.task_api.entity.Milestone;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneDto {
    private final Long id;
    private final Long projectId;
    private final String name;
    private final LocalDate createdAt;

    public MilestoneDto(Milestone milestone) {
        this.id = milestone.getId();
        this.projectId = milestone.getProject().getId();
        this.name = milestone.getName();
        this.createdAt = milestone.getCreatedAt();
    }

}
