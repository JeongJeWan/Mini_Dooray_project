package com.nhnacademy.gateway.service.task;

import com.nhnacademy.gateway.adapter.MilestoneAdapter;
import com.nhnacademy.gateway.dto.milestone.CreateMilestoneRequest;
import com.nhnacademy.gateway.dto.milestone.MilestoneDto;
import com.nhnacademy.gateway.dto.milestone.ModifyMilestoneRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {
    private final MilestoneAdapter milestoneAdapter;

    public void createMilestone(final CreateMilestoneRequest createMilestoneRequest) {
        milestoneAdapter.createMilestone(createMilestoneRequest);
    }

    public MilestoneDto findMilestone(Long id) {
        return milestoneAdapter.findMilestone(id);
    }

    public List<MilestoneDto> findMilestonesByProjectId(Long projectId) {
        return milestoneAdapter.findMilestoneListProjectId(projectId);
    }

    public void modifyMilestone(ModifyMilestoneRequest modifyMilestoneRequest) {
        milestoneAdapter.modifyMilestone(modifyMilestoneRequest);
    }

    public void deleteMilestone(Long id) {
        milestoneAdapter.deleteMilestone(id);
    }
}
