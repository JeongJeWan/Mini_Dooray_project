package com.nhnacademy.task_api.dto.milestone;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MilestoneListDto {
    private final List<MilestoneDto> milestoneDtoList;

}
