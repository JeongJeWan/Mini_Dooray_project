package com.nhnacademy.task_api.dto.milestone;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyMilestoneDto {
    private Long id;
    private String name;
    private LocalDate createdAt;
}
