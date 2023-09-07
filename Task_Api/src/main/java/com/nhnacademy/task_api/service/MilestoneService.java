package com.nhnacademy.task_api.service;

import com.nhnacademy.task_api.dto.milestone.CreateMilestoneDto;
import com.nhnacademy.task_api.dto.milestone.MilestoneDto;
import com.nhnacademy.task_api.dto.milestone.ModifyMilestoneDto;

import java.util.List;

public interface MilestoneService {
    void createMilestone(CreateMilestoneDto createMilestone);
    List<MilestoneDto> findMilestoneByProjectId(Long projectId);
    MilestoneDto findMilestone(Long id);
    void modifyMilestone(ModifyMilestoneDto modifyMilestone);
    void deleteMilestone(Long id);
}
