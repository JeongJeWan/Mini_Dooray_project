package com.nhnacademy.gateway.dto.milestone;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ModifyMilestoneRequest {

    @NotNull
    private Long id;
    @NotNull
    private String name;
//    private LocalDate createdAt;
}
