package com.nhnacademy.gateway.dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String name;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
