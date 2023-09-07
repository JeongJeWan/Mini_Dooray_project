package com.nhnacademy.gateway.dto.milestone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MilestoneDto {

    private Long id;
    private Long projectId;
    private String name;
    private LocalDate createdAt;
}
